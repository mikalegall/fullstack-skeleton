package fi.dev.academy.vaccinationdatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplicationVaccinationDataBase {

	public static void main(String[] args) {
		SpringApplication.run(MainApplicationVaccinationDataBase.class, args);
	}

	// CommandLineRunner will be executed right after the startup so it is a good place to
	// do some initial setups
	@Bean
	public CommandLineRunner doSomeSetUp() {
		return (args) -> {
			System.out.println("This is the place where to init e.g. DataBase, generate test users for Dev etc.");

		};
	}

}
