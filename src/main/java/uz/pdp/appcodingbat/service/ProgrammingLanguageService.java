package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.ProgrammingLanguage;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProgrammingLanguageDto;
import uz.pdp.appcodingbat.repository.ProgrammingLanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    /**
     * Bu method barcha Dasturlash tillarni qaytaradi
     *
     * @return programmingLanguages
     */
    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguageRepository.findAll();
    }

    /**
     * Bu method bitta dasturlash tilini id orqali qaytaradi.
     * @param id
     * @return programmingLanguage
     */
    public ProgrammingLanguage getProgrammingLanguageById(Integer id) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        return (ProgrammingLanguage) optionalProgrammingLanguage.orElse(null);
    }

    /**
     * BU method yangi dasturlash tilini qo'shadi.
     * A new programming language is added by this method.
     * @param programmingLanguageDto
     * @return ApiResponse
     */
    public ApiResponse addProgrammingLanguage(ProgrammingLanguageDto programmingLanguageDto) {

        if (programmingLanguageRepository.existsByName(programmingLanguageDto.getName()))
            return new ApiResponse(false, "this programming language already exist!");

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(programmingLanguageDto.getName());
        programmingLanguageRepository.save(programmingLanguage);

        return new ApiResponse(true, "programming language added successfully!");
    }

    /**
     * A programming language is edited by this method.
     * @param id
     * @param programmingLanguageDto
     * @return ApiResponse
     */
    public ApiResponse editeProgrammingLanguage(Integer id, ProgrammingLanguageDto programmingLanguageDto) {

        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse(false, "programing language not found!");

        if (programmingLanguageRepository.existsByNameAndIdNot(programmingLanguageDto.getName(), id))
            return new ApiResponse(false, "this programming language already exist!");

        ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) optionalProgrammingLanguage.get();
        programmingLanguage.setName(programmingLanguageDto.getName());
        programmingLanguageRepository.save(programmingLanguage);

        return new ApiResponse(true, "programming language edited!");
    }

    /**
     * Bu method orqali dasturlash tili o'chiriladi
     * A programming language is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteProgrammingLanguage(Integer id) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse(false, "programming language not found!");

        programmingLanguageRepository.deleteById(id);
        return new ApiResponse(true, "programming language deleted!");
    }
}
