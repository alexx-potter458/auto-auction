package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.exception.UserNotFoundException;
import pottertech.autoauctions.mapper.UserMapper;
import pottertech.autoauctions.repository.UserRepository;
import pottertech.autoauctions.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDtoList = userRepository.findAll().stream()
                .map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList());

        if (userDtoList.isEmpty()) {
            throw new UserNotFoundException(String.format("No user found!"));
        }

        return userDtoList;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return this.userMapper.userToUserDto(this.userRepository.save(this.userMapper.userDtoToUser(userDto)));
    }
}
