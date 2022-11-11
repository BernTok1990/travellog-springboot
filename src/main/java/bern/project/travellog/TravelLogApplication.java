package bern.project.travellog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TravelLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelLogApplication.class, args);
    }
    
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                        .allowedOrigins("https://bern-travellog-frontend.herokuapp.com", "http://localhost:4200");
			}
		};
	}
}