package com.literaturagutendex.literaturagutendex;

import com.literaturagutendex.literaturagutendex.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturagutendexApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(LiteraturagutendexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();

		principal.showMenu();


	}
}


