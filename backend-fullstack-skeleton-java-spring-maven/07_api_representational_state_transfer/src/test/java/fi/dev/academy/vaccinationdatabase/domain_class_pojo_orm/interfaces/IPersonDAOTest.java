package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IPersonDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class IPersonDAOTest {

    @Autowired
    private IPersonDAO userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Person test = new Person("Test User", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE_USER");
        test.setRole("ROLE_USER");
        userRepository.save(test);
    }

    @Test
    void findByUsername() {
        Person person = userRepository.findByUsername("user");
        Assertions.assertThat(person.getRole()).isEqualTo("ROLE_USER");
    }
}