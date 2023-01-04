package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialUserDto {
    @NotNull(message = Constants.FIRSTNAME_NOT_NULL)
    private String firstname;

    @NotNull(message = Constants.LASTNAME_NOT_NULL)
    private String lastname;

    @NotNull(message = Constants.EMAIL_NOT_NULL)
    private String email;
}
