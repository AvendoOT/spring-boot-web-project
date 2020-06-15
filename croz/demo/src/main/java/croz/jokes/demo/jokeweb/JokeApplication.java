package croz.jokes.demo.jokeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starting point of a web application for adding, rating and displaying jokes.
 * Its style is simply defined with bootstrap, database is connected via Liquibase with
 * PostgreSQL.
 * Presentation layer is made with Thymeleaf.
 * @author Leon Kranjcevic
 */
@SpringBootApplication
public class JokeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokeApplication.class, args);
	}

}
