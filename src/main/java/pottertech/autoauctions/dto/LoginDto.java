package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
        import lombok.*;
import pottertech.autoauctions.Constants;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull(message = Constants.EMAIL_NOT_NULL)
    private String email;

    @NotNull(message = Constants.PASSWORD_NOT_NULL)
    private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
