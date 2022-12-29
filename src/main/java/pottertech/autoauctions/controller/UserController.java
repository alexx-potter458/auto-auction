package pottertech.autoauctions.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.service.implementation.UserServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<PartialUserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDTO) {
        return ResponseEntity.ok(this.userService.addUser(userDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<PartialUserDto> deleteUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userService.deleteUserByEmail(email));
    }
}
