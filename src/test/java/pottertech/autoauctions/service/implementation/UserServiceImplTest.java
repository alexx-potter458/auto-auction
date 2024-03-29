package pottertech.autoauctions.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.User;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.UserMapper;
import pottertech.autoauctions.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private User completeUser;
    private List<User> userList;
    private UserDto userDto;
    private PartialUserDto partialUserDto;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        completeUser = User.builder()
                .firstname("Alex")
                .lastname("Potter")
                .email("alex.potter@gmail.com")
                .id(Long.parseLong("1"))
                .build();
        userList.add(completeUser);

        userDto = UserDto.builder()
                .firstname("Alex")
                .lastname("Potter")
                .email("alex.potter@gmail.com")
                .password("mySecretPass")
                .build();

        partialUserDto = PartialUserDto.builder()
                .firstname("Alex")
                .lastname("Potter")
                .email("alex.potter@gmail.com")
                .build();
    }

    @Test
    @DisplayName("Get all users successfully")
    void getUsers() {
        when(this.userRepository.findAll()).thenReturn(this.userList);

        List<PartialUserDto> result = this.userService.getUsers();

        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    @DisplayName("Get all users empty")
    void getUsers1() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(UserException.class, () -> this.userService.getUsers());

        assertEquals(Constants.NO_USER_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Add user successfully")
    void addUser() {
        when(this.userRepository.findOneByEmailOrUsername(userDto.getEmail(), userDto.getUsername())).thenReturn(null);
        when(this.userMapper.userToUserDto(any())).thenReturn(this.userDto);

        UserDto response = this.userService.addUser(this.userDto);

        assertNotNull(response);
        assertEquals(response, userDto);
    }

    @Test
    @DisplayName("Add user failed")
    void addUser2() {
        when(this.userRepository.findOneByEmailOrUsername(userDto.getEmail(), userDto.getUsername())).thenReturn(completeUser);

        Exception exception = assertThrows(UserException.class, () -> this.userService.addUser(this.userDto));

        assertEquals(Constants.USER_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    @DisplayName("Delete user successfully")
    void deleteUserByEmail() {
        when(this.userRepository.findOneByEmail(completeUser.getEmail())).thenReturn(completeUser);
        when(this.userMapper.userToPartialUserDto(completeUser)).thenReturn(this.partialUserDto);

        PartialUserDto partialUserDto = this.userService.deleteUserByEmail(completeUser.getEmail());

        assertNotNull(partialUserDto);
        assertEquals(partialUserDto.getFirstname(), completeUser.getFirstname());
        assertEquals(partialUserDto.getEmail(), completeUser.getEmail());
    }

    @Test
    @DisplayName("Delete user fails")
    void deleteUserByEmail2() {
        when(this.userRepository.findOneByEmail(completeUser.getEmail())).thenReturn(null);

        Exception exception = assertThrows(UserException.class, () -> this.userService.deleteUserByEmail(completeUser.getEmail()));

        assertEquals(Constants.NO_USER_FOUND_FOR_EMAIL, exception.getMessage());
    }
}