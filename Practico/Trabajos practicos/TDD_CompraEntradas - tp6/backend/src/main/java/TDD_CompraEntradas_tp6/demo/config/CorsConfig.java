package TDD_CompraEntradas_tp6.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")   // cualquiera
                        .allowedMethods("*")          // GET, POST, PUT, DELETE, OPTIONS, etc.
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowCredentials(false)      // DEJAR EN false si usás "*"
                        .maxAge(3600);                // cache del preflight
            }
        };
    }
}

