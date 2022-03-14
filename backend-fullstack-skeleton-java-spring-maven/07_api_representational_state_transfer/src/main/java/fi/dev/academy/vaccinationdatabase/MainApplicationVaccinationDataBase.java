package fi.dev.academy.vaccinationdatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.dev.academy.vaccinationdatabase.common.utilities.SetUpDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"fi.dev.academy.vaccinationdatabase"})
public class MainApplicationVaccinationDataBase {

    /*    @Autowired
        private IOrderDAO orderRepository;
    */
    @Autowired
    private SetUpDB setUpDB;

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(MainApplicationVaccinationDataBase.class, args);
    }

    // CommandLineRunner will be executed right after the startup so it is a good place to
    // do some initial setups
    @Bean
//    public CommandLineRunner doSomeSetUp(IPersonDAO userRepository, IVaccinationDAO vaccinationRepository) {
    public CommandLineRunner doSomeSetUp() {
        return (args) -> {
            System.out.println("This is the place where to init e.g. DataBase, generate test users for Dev etc.");
            setUpDB.initDB();
/*
            Person admin = new Person("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ROLE_ADMIN");
            admin.setRole("ROLE_ADMIN");

            Person user = new Person("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE_USER");
            user.setRole("ROLE_USER");

            System.out.println("User = " + user);
            System.out.println("Admin = " + admin);


            userRepository.save(admin);
            userRepository.save(user);
*/

/*
            Order order = new Order();
            order.setHealthCareDistrict("TAYS");
            order.setInjections(4);
            order.setOrderNumber(2);
            order.setOrderedAmpuleBottleId("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
            order.setResponsiblePerson("Tarvo Puro");
            order.setVaccine("Antiqua");
            orderRepository.save(order);


            // Vaccination ampule for doses: orderedAmpuleBottleId = Vaccination.sourceBottle
            List<Order> linkedOrder = orderRepository.findByOrderedAmpuleBottleId("1251aa6c-ebaf-4e33-89d3-d6f210497b94");


            Vaccination vaccination5864 = new Vaccination();
            vaccination5864.setGender("female");
            vaccination5864.setSourceBottle("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
            vaccination5864.setVaccinationId("303d3a3f-4a04-459c-8696-468f484af909");

            // Vaccination ampule for doses: orderedAmpuleBottleId = Vaccination.sourceBottle
            vaccination5864.setWhichOrderIncludesTheseVaccinationsLinked(linkedOrder.get(0));
            vaccinationRepository.save(vaccination5864);


            Vaccination vaccination7333 = new Vaccination();
            vaccination7333.setGender("male");
            vaccination7333.setSourceBottle("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
            vaccination7333.setVaccinationId("d1af957e-f9b4-4322-af8f-397bd7b6a405");

            // Vaccination ampule for doses: orderedAmpuleBottleId = Vaccination.sourceBottle
            vaccination7333.setWhichOrderIncludesTheseVaccinationsLinked(linkedOrder.get(0));
            vaccinationRepository.save(vaccination7333);


            Vaccination vaccination7728 = new Vaccination();
            vaccination7728.setGender("female");
            vaccination7728.setSourceBottle("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
            vaccination7728.setVaccinationId("dd95004a-8de9-4121-9ec9-071beb827ffb");

            // Vaccination ampule for doses: orderedAmpuleBottleId = Vaccination.sourceBottle
            vaccination7728.setWhichOrderIncludesTheseVaccinationsLinked(linkedOrder.get(0));
            vaccinationRepository.save(vaccination7728);
*/
        };
    }

}
