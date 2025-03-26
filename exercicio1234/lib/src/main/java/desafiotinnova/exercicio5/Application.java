package desafiotinnova.exercicio5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "desafiotinnova.exercicio5")
@EntityScan(basePackages = "desafiotinnova.exercicio5.model") 
@EnableJpaRepositories(basePackages = "desafiotinnova.exercicio5.repository")
public class Application {

    public static void main(String[] args) {
        // Starts the Spring Boot application
        SpringApplication.run(Application.class, args);
    }
}