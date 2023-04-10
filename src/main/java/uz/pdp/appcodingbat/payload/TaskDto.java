package uz.pdp.appcodingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotNull(message = "name should not be empty!")
    private String name;

    @NotNull(message = "text should not be empty!")
    private String text;

    @NotNull(message = "answerBody should not be empty!")
    private String answerBody;

    @NotNull(message = "departmentId should not be empty!")
    private Integer departmentId;
}
