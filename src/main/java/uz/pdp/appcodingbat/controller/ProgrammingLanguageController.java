package uz.pdp.appcodingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.ProgrammingLanguage;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProgrammingLanguageDto;
import uz.pdp.appcodingbat.service.ProgrammingLanguageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/programmingLanguage")
public class ProgrammingLanguageController {
    @Autowired
    ProgrammingLanguageService programmingLanguageService;

    /**
     * Bu method barcha Dasturlash tillarni qaytaradi
     * This method returns all programming language.
     *
     * @return programmingLanguages
     */
    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getProgramingLanguages(){
        List<ProgrammingLanguage> programmingLanguages = programmingLanguageService.getProgrammingLanguages();
        return ResponseEntity.ok(programmingLanguages);
    }

    /**
     * Bu method bitta dasturlash tilini id orqali qaytaradi.
     * This method returns one programming language by id.
     * @param id
     * @return programmingLanguage
     */
    @GetMapping("/{id}")
    public HttpEntity<ProgrammingLanguage> getProgrammingLanguageById(
            @PathVariable Integer id){
        ProgrammingLanguage programmingLanguage = programmingLanguageService.getProgrammingLanguageById(id);
        return ResponseEntity.ok(programmingLanguage);
    }

    /**
     * BU method yangi dasturlash tilini qo'shadi.
     * A new programming language is added by this method.
     * @param programmingLanguageDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addProgrammingLanguage(
            @Valid @RequestBody ProgrammingLanguageDto programmingLanguageDto){
        ApiResponse apiResponse = programmingLanguageService.addProgrammingLanguage(programmingLanguageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * Bu method orqali dasturlash tili tahrirlanadi
     * A programming language is edited by this method.
     * @param id
     * @param programmingLanguageDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeProgrammingLanguage(
            @PathVariable Integer id,
            @Valid @RequestBody ProgrammingLanguageDto programmingLanguageDto){
        ApiResponse apiResponse = programmingLanguageService.editeProgrammingLanguage(id, programmingLanguageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * Bu method orqali dasturlash tili o'chiriladi
     * A programming language is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteProgrammingLanguage(
            @PathVariable Integer id){
        ApiResponse apiResponse = programmingLanguageService.deleteProgrammingLanguage(id);
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
