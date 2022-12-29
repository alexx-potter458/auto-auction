package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialUserDto {
    @NotNull(message = "Firstname should not be null")
    private String firstname;

    @NotNull(message = "Lastname should not be null")
    private String lastname;

    @NotNull(message = "Email should not be null")
    private String email;
}
