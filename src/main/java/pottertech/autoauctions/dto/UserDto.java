package pottertech.autoauctions.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import pottertech.autoauctions.Constants;

@Getter
@Setter
@Builder
@ AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = Constants.FIRSTNAME_NOT_NULL)
    private String firstname;

    @NotNull(message = Constants.LASTNAME_NOT_NULL)
    private String lastname;

    @NotNull(message = Constants.EMAIL_NOT_NULL)
    private String email;

    @NotNull(message = Constants.EMAIL_NOT_NULL)
    private String username;

    @NotNull(message = Constants.PASSWORD_NOT_NULL)
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
