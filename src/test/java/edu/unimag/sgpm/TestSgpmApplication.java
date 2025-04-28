package edu.unimag.sgpm;

import org.springframework.boot.SpringApplication;

public class TestSgpmApplication {

	public static void main(String[] args) {
		SpringApplication.from(SgpmApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
