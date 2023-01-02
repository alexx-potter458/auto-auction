package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
        import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull(message = "Email should not be null")
    private String email;

    @NotNull(message = "User password should not be null")
    private String password;
}
