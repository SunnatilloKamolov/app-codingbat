package uz.pdp.appcodingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {
    @NotNull(message = "request shouldn't be empty.")
    private String request;

    @NotNull(message = "response shouldn't be empty.")
    private String response;

    @NotNull(message = "taskId shouldn't be empty.")
    private Integer taskId;
}
