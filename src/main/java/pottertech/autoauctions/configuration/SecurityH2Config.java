package pottertech.autoauctions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("h2")
public class SecurityH2Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("guest")
            .password(passwordEncoder.encode("12345"))
            .roles("USER")
            .build());
        manager.createUser(User.withUsername("admin")
            .password(passwordEncoder.encode("12345"))
            .roles("USER", "ADMIN")
            .build());
        return manager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .authorizeHttpRequests()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/").permitAll()
            .requestMatchers("/filter").permitAll()
            .requestMatchers("/approve").permitAll()
            .requestMatchers("/buy").permitAll()
            .requestMatchers("/car/**").permitAll()
            .requestMatchers("/user/login").permitAll()
            .requestMatchers("/user/logout").permitAll()
            .requestMatchers("/user/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .headers(headers -> headers.frameOptions().sameOrigin())
            .httpBasic(withDefaults())
            .build();
    }
}
