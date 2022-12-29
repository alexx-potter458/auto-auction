package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.exception.UserException;
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
    public List<PartialUserDto> getUsers() {
        List<PartialUserDto> userDtoList = userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.userToPartialUserDto(user))
                .collect(Collectors.toList());

        if (userDtoList.isEmpty())
            throw new UserException(Constants.NO_USER_FOUND);

        return userDtoList;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = this.userRepository.findOneByEmail(userDto.getEmail());

        if (user != null)
            throw new UserException(Constants.USER_ALREADY_EXISTS);

        return this.userMapper.userToUserDto(this.userRepository.save(this.userMapper.userDtoToUser(userDto)));
    }

    @Override
    public PartialUserDto deleteUserByEmail(String email) {
        User user = this.userRepository.findOneByEmail(email);

        if (user == null)
            throw new UserException(Constants.NO_USER_FOUND_FOR_EMAIL);

        this.userRepository.delete(user);
        return this.userMapper.userToPartialUserDto(user);
    }
}
