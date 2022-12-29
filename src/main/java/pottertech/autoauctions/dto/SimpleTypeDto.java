package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTypeDto {
    @NotNull(message = "Car manufacturer name should not be null")
    private String name;
}
