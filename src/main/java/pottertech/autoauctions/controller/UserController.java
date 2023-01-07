package pottertech.autoauctions.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.LoginDto;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.entity.Token;
import pottertech.autoauctions.service.implementation.UserServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "🗄️User controller", description = "All the user related endpoints can be found here.")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @Operation(summary = "🗃️ Get all users", description = "This will bring all users after they are mapped with a partial DTO.")
    public ResponseEntity<List<PartialUserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping
    @Operation(summary = "🗃️ Add user", description = "This will add a user in the database.")
    public ResponseEntity<UserDto> addUser(@Parameter(description = "It contains all the data needed to create a new user.") @Valid @RequestBody UserDto userDTO) {
        return ResponseEntity.ok(this.userService.addUser(userDTO));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "🗃️ Delete user", description = "This will delete a user by email given.")
    public ResponseEntity<PartialUserDto> deleteUserByEmail(@Parameter(description = "It is the email received needed in order to delete the user.") @PathVariable String email) {
        return ResponseEntity.ok(this.userService.deleteUserByEmail(email));
    }

    @PostMapping("/login")
    @Operation(summary = "🗃️ Login a user", description = "This will return or create a new token which is needed to work with 🗄️Reports, simulating a user authentication.")
    public ResponseEntity<Token> login(@Parameter(description = "It contains all the data needed to login a user.") @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(this.userService.login(loginDto));
    }
}
