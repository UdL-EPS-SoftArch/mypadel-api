package cat.udl.eps.softarch.mypadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyPadelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPadelApiApplication.class, args);
	}

}
