package fi.dev.academy.vaccinationdatabase.classess.interfaces;

import fi.dev.academy.vaccinationdatabase.classess.vaccination.Vaccination;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IVaccinationDAOTest {

    @Autowired
    private IVaccinationDAO vaccinationRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Vaccination test = new Vaccination();
        test.setGender("fluid");
        test.setSourceBottle("Kombuza");
        vaccinationRepository.save(test);
    }


    @Test
    void findBySourceBottle() {
        List<Vaccination> vaccination = vaccinationRepository.findBySourceBottle("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
        vaccination.get(0).setGender("female");
    }
}