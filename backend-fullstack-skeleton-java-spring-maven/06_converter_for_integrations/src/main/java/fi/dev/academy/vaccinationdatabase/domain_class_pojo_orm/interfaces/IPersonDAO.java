package fi.dev.academy.vaccinationdatabase.classes.interfaces;

import fi.dev.academy.vaccinationdatabase.classes.person.Person;
import org.springframework.data.repository.Repository;

public interface IPersonDAO extends Repository<Person, Long> {

    Person save(Person person);

    Person findByUsername(String username);
}
