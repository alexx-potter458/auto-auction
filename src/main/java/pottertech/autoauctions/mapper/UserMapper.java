package pottertech.autoauctions.mapper;

import org.springframework.stereotype.Component;
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
                .isAdmin(userDto.isAdmin())
                .build();
    }

    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .isAdmin(user.isAdmin())
                .build();
    }
}
