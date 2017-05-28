package norbertilies.tickets;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class TicketsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}
}
