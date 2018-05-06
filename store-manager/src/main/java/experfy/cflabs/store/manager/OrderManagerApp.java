/**
 * 
 */
package experfy.cflabs.store.manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * Order Manager Application
 * @author insignia
 *
 */
@SpringBootApplication
@EnableCircuitBreaker
public class OrderManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagerApp.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       return builder.build();
    }

    

}