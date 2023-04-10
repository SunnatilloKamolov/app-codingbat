package uz.pdp.appcodingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingLanguageDto {
    @NotNull(message = "name should not be empty!")
    private String name;

}
