package pottertech.autoauctions.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.Authority;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.repository.AuthorityRepository;

@Component
public class UserMapper {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    public User userDtoToUser(UserDto userDto) {
        Authority guestRole = this.authorityRepository.findByRole("ROLE_GUEST");

        return User.builder()
                .firstname(userDto.getFirstname())
                .username(userDto.getUsername())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authority(guestRole)
                .build();
    }

    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .firstname(user.getFirstname())
                .username(user.getUsername())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

    public PartialUserDto userToPartialUserDto(User user) {
        return PartialUserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
}
