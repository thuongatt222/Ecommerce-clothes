package ecom.clothes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoreResources() {
        return web -> web.ignoring()
                .requestMatchers(
                        "/actuator/**",         // Bỏ qua bảo mật cho Actuator
                        "/v3/**",               // Bỏ qua Swagger API v3
                        "/webjars/**",          // Bỏ qua các tài nguyên tĩnh
                        "/swagger-ui/**",       // Bỏ qua Swagger UI
                        "/swagger-ui/*swagger-initializer.js", // JS khởi tạo Swagger
                        "/swagger-ui.html" ,     // Bỏ qua file chính của Swagger UI
                        "/favicon.ico"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
