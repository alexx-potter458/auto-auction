package pottertech.autoauctions.mapper;

import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.User;

@Component
public class UserMapper {
    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .isAdmin(false)
                .build();
    }

    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
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
