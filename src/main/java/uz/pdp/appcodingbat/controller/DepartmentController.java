package uz.pdp.appcodingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Department;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.DepartmentDto;
import uz.pdp.appcodingbat.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * This method returns all departments.
     * @return departments
     */
    @GetMapping
    public HttpEntity<List<Department>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * This method returns one department by id.
     * @param id
     * @return department
     */
    @GetMapping("/{id}")
    public HttpEntity<Department> getDepartmentById(@PathVariable Integer id){
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    /**
     * This method saves a new department.
     * @param departmentDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * Department is edited by this method.
     * @param id
     * @param departmentDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeDepartment(
            @PathVariable Integer id,
            @Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editeDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * Department is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
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
