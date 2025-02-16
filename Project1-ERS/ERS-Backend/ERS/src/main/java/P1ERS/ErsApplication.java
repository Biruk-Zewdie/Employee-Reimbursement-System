package P1ERS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.biruk.models")
@ComponentScan("com.biruk")
public class ErsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ErsApplication.class, args);

		System.out.println("Employee Reimbursement System App is running...");
	}

}
