package fi.dev.academy.vaccinationdatabase.classes.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // JPA: ORM map into DB table (maven dependency spring-boot-starter-data-jpa)
@Table(name = "PERSONS", schema = "PUBLIC") // JPA (maven dependency spring-boot-starter-data-jpa)
@Data // Lombok for getters and setters (maven dependency org.projectlombok)
public class Person {

    // Maven dependency: spring-boot-starter-security
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    // https://www.baeldung.com/jpa-unique-constraints
    private String username;

    // Maven dependency: com.fasterxml.jackson.core / jackson-databind
    @JsonIgnore // Do not print to console, into logs, or export through JSON serialization
    @Column(nullable = false, name = "password")
    private String passwordHash;

    //    @Column(nullable=false)
    //    private String[] roles;
    private String role;

    private LocalDateTime created, lastTimeEdited;
    private String familyName, givenName, name, nickname, telephone, email;
    private Boolean recordDeleted;

    public Person() {
        super();
    }

    // TODO Remember to remove me
    // Just for testing with CommandLineRunner
    public Person(String username, String passwordHash, String role_admin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public void setPassword(String password) {
        this.passwordHash = PASSWORD_ENCODER.encode(password);
    }


}
