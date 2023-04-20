package pottertech.autoauctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.PartialUserDto;
import pottertech.autoauctions.dto.UserDto;
import pottertech.autoauctions.service.implementation.UserServiceImpl;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<PartialUserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute UserDto userDTO) {
        try {
            this.userService.addUser(userDTO);
        } catch(Exception exception) {
            return "redirect:/user/register?error";
        }

        return "redirect:/user/login?registered";
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<PartialUserDto> deleteUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.userService.deleteUserByEmail(email));
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/register")
    public String showRegisterForm(){ return "register"; }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "redirect:/?denied";
    }
}
