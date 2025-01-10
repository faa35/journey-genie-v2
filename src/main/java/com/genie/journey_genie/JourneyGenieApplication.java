
package com.genie.journey_genie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class JourneyGenieApplication {

	public static void main(String[] args) {
		SpringApplication.run(JourneyGenieApplication.class, args);
	}
	@Bean
    public WebMvcConfigurer corsFixer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") 
				.allowedOrigins("https://acanto7.vercel.app")
				.allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
        };
    }

}
