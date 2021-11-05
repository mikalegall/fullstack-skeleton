package fi.dev.academy.vaccinationdatabase;

import fi.dev.academy.vaccinationdatabase.classes.interfaces.IPersonDAO;
import fi.dev.academy.vaccinationdatabase.classes.person.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"fi.dev.academy.vaccinationdatabase"})
public class MainApplicationVaccinationDataBase {

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationVaccinationDataBase.class, args);
    }

    // CommandLineRunner will be executed right after the startup so it is a good place to
    // do some initial setups
    @Bean
    public CommandLineRunner doSomeSetUp(IPersonDAO userRepository) {
        return (args) -> {
            System.out.println("This is the place where to init e.g. DataBase, generate test users for Dev etc.");

            Person admin = new Person("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ROLE_ADMIN");
            admin.setRole("ROLE_ADMIN");

            Person user = new Person("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE_USER");
            user.setRole("ROLE_USER");

            System.out.println("User = " + user);
            System.out.println("Admin = " + admin);

/*
            userRepository.save(admin);
            userRepository.save(user);
*/
        };
    }

}
