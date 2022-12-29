package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Firstname should not be null")
    private String firstname;

    @NotNull(message = "Lastname should not be null")
    private String lastname;

    @NotNull(message = "Email should not be null")
    private String email;

    @NotNull(message = "User password should not be null")
    private String password;

    @NotNull(message = "User password should not be null")
    private boolean isAdmin;
}
