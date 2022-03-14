package fi.dev.academy.vaccinationdatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
//import fi.dev.academy.vaccinationdatabase.common.utilities.SetUpDB;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"fi.dev.academy.vaccinationdatabase"})
public class MainApplicationVaccinationDataBase {
/*

    @Autowired
    private SetUpDB setUpDB;
*/

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(MainApplicationVaccinationDataBase.class, args);
    }

/*
    // CommandLineRunner will be executed right after the startup so it is a good place to
    // do some initial setups
    @Bean
    public CommandLineRunner doSomeSetUp() {
        return (args) -> {
            System.out.println("This is the place where to init e.g. DataBase, generate test users for Dev etc.");
            setUpDB.initDB();
        };
    }
*/

}
