package uz.pdp.appcodingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Done;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.DoneDto;
import uz.pdp.appcodingbat.service.DoneService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/done")
public class DoneController {
    @Autowired
    DoneService doneService;

    /**
     * This method returns all done.
     * @return all done
     */
    @GetMapping
    public HttpEntity<List<Done>> getAll(){
        List<Done> all = doneService.getAll();
        return ResponseEntity.ok(all);
    }

    /**
     * This method returns one done by id.
     * @return all done
     */
    @GetMapping("/{id}")
    public HttpEntity<Done> getById(@PathVariable Integer id){
        Done done = doneService.getById(id);
        return ResponseEntity.ok(done);
    }

    /**
     * This method adds done.
     * @param doneDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody DoneDto doneDto){
        ApiResponse apiResponse = doneService.add(doneDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * This method edits done by id.
     * @param id
     * @param doneDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edite(@PathVariable Integer id, @Valid @RequestBody DoneDto doneDto){
        ApiResponse apiResponse = doneService.edite(id, doneDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * This method deletes done by id.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = doneService.delete(id);
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
