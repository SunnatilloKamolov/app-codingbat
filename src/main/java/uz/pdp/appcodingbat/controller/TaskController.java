package uz.pdp.appcodingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.service.TaskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    /**
     * This method returns list of tasks.
     *
     * @return Tasks
     */
    @GetMapping
    public ResponseEntity<List<uz.pdp.appcodingbat.entity.Task>> getTasks(){
        List<uz.pdp.appcodingbat.entity.Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * This method returns one task by id.
     *
     * @param id
     * @return Task
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        uz.pdp.appcodingbat.entity.Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * This method adds new test.
     * @param taskDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addTask(@Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * This method edits task by id.
     * @param id
     * @param taskDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeTask(@PathVariable Integer id, @Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.editeTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * This method deletes task by id.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
