package pottertech.autoauctions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pottertech.autoauctions.service.implementation.UserServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("mysql")
public class SecurityJpaConfig {
    private final UserServiceImpl userDetailsService;

    public SecurityJpaConfig(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/approve").hasRole("ADMIN")
                    .requestMatchers("/buy").hasAnyRole("ADMIN", "GUEST")
                    .requestMatchers("/car/**").hasRole("ADMIN")
                    .requestMatchers("/user/login").permitAll()
                    .requestMatchers("/user/logout").permitAll()
                    .requestMatchers("/user/register").permitAll()
                    .anyRequest().authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/perform_login")
                .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/access-denied")
                .and()
                .httpBasic(withDefaults())
                .build();
    }
}

