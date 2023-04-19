package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTypeDto {
    @NotNull(message = Constants.NAME_NOT_NULL)
    private String name;


}
