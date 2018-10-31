package wikidrinks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ControladorWikidrinks.class)
public class AplicacionWikidrinks {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionWikidrinks.class, args);
	}
}
