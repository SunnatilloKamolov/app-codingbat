package uz.pdp.appcodingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "firstName shouldn't be empty.")
    private String firstName;

    @NotNull(message = "lastName shouldn't be empty.")
    private String lastName;

    @NotNull(message = "email shouldn't be empty.")
    private String email;

    @NotNull(message = "password shouldn't be empty.")
    private String password;
}
