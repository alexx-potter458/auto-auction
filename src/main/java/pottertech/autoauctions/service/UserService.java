package pottertech.autoauctions.service;

import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import java.util.List;

public interface  UserService {
    List<PartialUserDto> getUsers();

    UserDto addUser(UserDto userDto);

    PartialUserDto deleteUserByEmail(String email);
}
