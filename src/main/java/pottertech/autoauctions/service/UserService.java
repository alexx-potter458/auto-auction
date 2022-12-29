package pottertech.autoauctions.service;

import org.springframework.stereotype.Service;
import pottertech.autoauctions.dto.UserDto;

import java.util.List;

public interface  UserService {
    public List<UserDto> getUsers();
    public UserDto addUser(UserDto userDto);
}
