package com.aluracursos.gutendexapi_ebook;

import com.aluracursos.gutendexapi_ebook.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Se puede consultar la app screenmatch para entender algunos conceptos que aqui se usan

@SpringBootApplication
public class GutendexapiEbookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GutendexapiEbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.Menu();
	}
}
