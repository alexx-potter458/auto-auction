package pottertech.autoauctions.service;

import pottertech.autoauctions.dto.LoginDto;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.Token;

import java.util.List;

public interface  UserService {
    List<PartialUserDto> getUsers();

    UserDto addUser(UserDto userDto);

    PartialUserDto deleteUserByEmail(String email);

    Token login(LoginDto loginDto);
}
