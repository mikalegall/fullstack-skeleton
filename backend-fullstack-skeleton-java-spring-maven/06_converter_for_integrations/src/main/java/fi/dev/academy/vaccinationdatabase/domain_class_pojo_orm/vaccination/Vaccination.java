package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VACCINATIONS", schema = "PUBLIC")
@Data
public class Vaccination {

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @JsonIgnore
    private Long version;

    private String sourceBottle; // Order.orderedAmpuleBottleId
    private String vaccinationId, gender;
    private LocalDateTime vaccinationDate;

    // Many vaccination events from one ordered ampoule bottle
    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne
    // JoinColumn makes a connection via a foreign key (otherwise Hibernate will use @JoinTable as default)
    // JoinColumn also creates a new column 'fk_orderedAmpuleBottleId' in VACCINATIONS table
    // JoinColumn is the side of the relation that owns (Owner) the foreign key in the database
    // @JoinColumn(name="fk_orderedAmpuleBottleId", referencedColumnName="orderedAmpuleBottleId", nullable = false)
    @JsonIgnoreProperties("vaccinations") // Otherwise it might end up looping endlessly with linked mapping
    @JoinColumn(name = "fk_orderedAmpuleBottleId")
    private Order whichOrderIncludesTheseVaccinationsLinked;

}
