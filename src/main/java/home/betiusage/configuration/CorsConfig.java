package home.betiusage.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * This method is used to configure the CORS settings for the application.
     * It allows requests from the specified origin, methods, headers and credentials.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("http://localhost:4242", "https://better-time-usage-2ph6x.ondigitalocean.app")
            .allowedMethods(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "HEAD",
                "OPTIONS"
            )
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
