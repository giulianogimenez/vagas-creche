package br.edu.fatecsjc.creche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableAutoConfiguration (exclude = LiquibaseAutoConfiguration.class)
@EnableSwagger2
public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}

	@GetMapping("/")
	@ResponseBody
	public String home() {
		return "home";
	}
}
