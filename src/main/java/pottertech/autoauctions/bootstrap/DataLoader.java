package pottertech.autoauctions.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pottertech.autoauctions.entity.Authority;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.repository.UserRepository;
import pottertech.autoauctions.repository.AuthorityRepository;

@AllArgsConstructor
@Component
@Profile("mysql")
public class DataLoader implements CommandLineRunner {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority guestRole = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());

            User admin = User.builder()
                    .username("admin")
                    .email("admin@gmail.com")
                    .firstname("Admin")
                    .lastname("Admin")
                    .password(passwordEncoder.encode("1234"))
                    .authority(adminRole)
                    .build();

            User guest = User.builder()
                    .username("guest")
                    .email("guest@gmail.com")
                    .firstname("Guest")
                    .lastname("Guest")
                    .password(passwordEncoder.encode("12345"))
                    .authority(guestRole)
                    .build();

            userRepository.save(admin);
            userRepository.save(guest);
        }
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}

