package br.com.danielamaral.webspider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebspiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebspiderApplication.class, args);
	}

}
