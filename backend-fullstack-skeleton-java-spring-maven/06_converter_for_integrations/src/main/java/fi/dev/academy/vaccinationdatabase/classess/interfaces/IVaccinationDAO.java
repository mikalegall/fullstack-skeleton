package fi.dev.academy.vaccinationdatabase.classess.interfaces;

import fi.dev.academy.vaccinationdatabase.classess.vaccination.Vaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVaccinationDAO extends CrudRepository<Vaccination, Long> {

    List<Vaccination> findBySourceBottle(String sourceBottle);

}