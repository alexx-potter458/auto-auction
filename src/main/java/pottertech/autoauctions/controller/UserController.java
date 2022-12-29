package pottertech.autoauctions.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.service.implementation.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUser() {
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDTO) {
        UserDto user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }
}
