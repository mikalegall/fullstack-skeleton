package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
 https://en.wikipedia.org/wiki/Plain_old_Java_object
 The Bean class is a sub-set of the POJO class.
 All Bean files can be POJOs, but not all POJOs are beans.
 Bean files has to implement Serializable interface but
 POJOs do not have to extend any implementations not even through annotations.
 The POJO is used when wanted to provide direct access to users by using names of the class member variables,
 Bean is used when wanted to provide partial access to instance variables via getters and setters, in other words,
 hide instance variables by encapsulation.
 https://www.javatpoint.com/pojo-in-java
*/

@Entity
@Table(name = "ORDERS", schema = "PUBLIC")
@Data
public class Order {

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Optimistic locking (on concurrent use prevent to update the older one)
    // https://www.baeldung.com/jpa-optimistic-locking
    // version=0 --> version=1
    // org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value
    // mapping was incorrect) : [fi.dev.academy.vaccinationdatabase.classes.order.Order#1]
    @Version
    @JsonIgnore
    private Long version;

    private String orderedAmpuleBottleId; //Vaccination ampule for doses = Vaccination.sourceBottle
    private String responsiblePerson, healthCareDistrict, vaccine;
    private int orderNumber, injections;
    private LocalDateTime arrived;

    // https://techyowls.com/post/jpa-one-to-many/
    // One ordered ampoule bottle can have many vaccination events
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "whichOrderIncludesTheseVaccinationsLinked")
    // Which vaccinations has been dosed by this order (ampule for doses)
    // https://stackoverflow.com/questions/2749689/what-is-the-owning-side-in-an-orm-mapping
    private List<Vaccination> vaccinations;

}
