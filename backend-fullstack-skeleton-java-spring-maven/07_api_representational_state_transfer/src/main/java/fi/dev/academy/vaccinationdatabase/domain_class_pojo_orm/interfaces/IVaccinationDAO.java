package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVaccinationDAO extends CrudRepository<Vaccination, Long> {

    List<Vaccination> findBySourceBottle(String sourceBottle);

}