package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.springframework.data.repository.Repository;

public interface IPersonDAO extends Repository<Person, Long> {

    Person save(Person person);

    Person findByUsername(String username);
}
