package br.com.uniamerica.ibellembalagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IbellEmbalagensApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbellEmbalagensApiApplication.class, args);
	}

	@GetMapping("count-users")
	public String getCountUsers(){
		return "Olá Render.com com Spring Boot";
	}

}
