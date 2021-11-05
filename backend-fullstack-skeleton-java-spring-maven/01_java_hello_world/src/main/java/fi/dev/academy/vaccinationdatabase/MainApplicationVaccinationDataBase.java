package fi.dev.academy.vaccinationdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplicationVaccinationDataBase {

	public static void main(String[] args) {
		SpringApplication.run(MainApplicationVaccinationDataBase.class, args);

		System.out.println("Minimum 'Hello World' implementation");
	}

}
