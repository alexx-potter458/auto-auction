package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.LoginDto;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.Token;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.UserMapper;
import pottertech.autoauctions.repository.TokenRepository;
import pottertech.autoauctions.repository.UserRepository;
import pottertech.autoauctions.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

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

    @Override
    public Token login(LoginDto loginDto) {
        User user = this.userRepository.findOneByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        Token token = this.tokenRepository.findOneByUser(user);

        if(token != null)
            return token;

        Token token1 = Token.builder()
                    .user(user)
                    .name(UUID.randomUUID().toString())
                    .build();

        return this.tokenRepository.save(token1);
    }
}
