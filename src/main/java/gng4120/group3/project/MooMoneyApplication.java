package gng4120.group3.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MooMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MooMoneyApplication.class, args);
	}
}
