package aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ControladorComprobantes.class)
public class TpSeminarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSeminarioApplication.class, args);
	}
}
