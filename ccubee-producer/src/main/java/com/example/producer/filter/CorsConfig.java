package com.example.producer.filter;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ccubee
 * @since 20-2-10 15:32
 */
@NoArgsConstructor
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter getCorsFilter(){

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");

        config.addAllowedHeader("*");


        config.addAllowedMethod(HttpMethod.POST);

        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(corsSource);
    }

}
