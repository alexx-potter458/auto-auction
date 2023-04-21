package pottertech.autoauctions.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.Authority;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.exception.BadPayloadException;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.UserMapper;
import pottertech.autoauctions.repository.UserRepository;
import pottertech.autoauctions.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    Logger log = LoggerFactory.getLogger(UserService.class);

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
        log.info("---> Adding user...");
        User user = this.userRepository.findOneByEmailOrUsername(userDto.getEmail(), userDto.getUsername());

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("---> Loading user...");
        User user = this.userRepository.findOneByUsername(username);

        if (user == null)
            throw new BadPayloadException(Constants.NO_USER_FOUND);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.getEnabled(), user.getAccountNotExpired(),
                user.getCredentialsNotExpired(),user.getAccountNotLocked(),getAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        if (authorities == null){
            return new HashSet<>();
        } else if (authorities.size() == 0){
            return new HashSet<>();
        }
        else{
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }
}
