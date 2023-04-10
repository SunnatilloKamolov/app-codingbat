package uz.pdp.appcodingbat.controller;

import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    @Autowired
    UserService userService;

    /**
     * This method returns all users.
     *
     * @return Users
     */
    @GetMapping
    public ResponseEntity<List<uz.pdp.appcodingbat.entity.User>> getUsers(){
        List<uz.pdp.appcodingbat.entity.User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * This method returns one user by id.
     * @param id
     * @return User
     */
    @GetMapping("/{id}")
    public HttpEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * This method saves a new user.
     * @param userDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * This method edits user by id.
     * @param userDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeUser(@PathVariable Integer id,
                                             @Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.editeUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * This method deletes user by id.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }





    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
