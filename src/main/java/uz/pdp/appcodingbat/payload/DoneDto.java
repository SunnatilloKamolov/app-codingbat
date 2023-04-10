package uz.pdp.appcodingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoneDto {
    @NotNull(message = "userId shouldn't be empty.")
    private Integer userId;

    @NotNull(message = "taskId shouldn't be empty.")
    private Integer taskId;
}
