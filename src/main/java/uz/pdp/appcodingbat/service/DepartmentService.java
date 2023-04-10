package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Department;
import uz.pdp.appcodingbat.entity.ProgrammingLanguage;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.DepartmentDto;
import uz.pdp.appcodingbat.repository.DepartmentRepository;
import uz.pdp.appcodingbat.repository.ProgrammingLanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    /**
     * This method returns all departments.
     * @return departments
     */
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * This method returns one department by id.
     * @param id
     * @return department
     */
    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    /**
     * This method saves a new department.
     * @param departmentDto
     * @return ApiResponse
     */
    public ApiResponse addDepartment(DepartmentDto departmentDto) {

        boolean exist = departmentRepository.existsByNameAndProgrammingLanguageId(
                departmentDto.getName(),
                departmentDto.getProgrammingLanguageId());

        if (exist)
            return new ApiResponse(false, "this department already exist!");

        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(departmentDto.getProgrammingLanguageId());
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse(false, "Programming language not found!");

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setStarCount(departmentDto.getStarCount());
        department.setShortInfo(departmentDto.getShortInfo());
        department.setProgrammingLanguage((ProgrammingLanguage) optionalProgrammingLanguage.get());

        departmentRepository.save(department);
        return new ApiResponse(true, "department saved!");

    }

    /**
     * Department is edited by this method.
     * @param id
     * @param departmentDto
     * @return ApiResponse
     */
    public ApiResponse editeDepartment(Integer id, DepartmentDto departmentDto) {

        /*
         * is there an edited department.
         * */
        boolean exist = departmentRepository.existsByNameAndProgrammingLanguageIdAndIdNot(
                departmentDto.getName(),
                departmentDto.getProgrammingLanguageId(),
                id);

        if (exist)
            return new ApiResponse(false, "this department already exist!");

        /*
         * Programming language is found by programmingLanguageId.
         * */
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository
                .findById(departmentDto.getProgrammingLanguageId());
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse(false, "Programming language not found!");
        ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) optionalProgrammingLanguage.get();

        /*
         * Department is found by id.
         * */
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            return new ApiResponse(false, "department not found!");

        /*
         * to edit department
         * */
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setStarCount(departmentDto.getStarCount());
        department.setShortInfo(departmentDto.getShortInfo());
        department.setProgrammingLanguage(programmingLanguage);

        departmentRepository.save(department);
        return new ApiResponse(true, "department edited!");
    }

    /**
     * Department is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            return new ApiResponse(false, "department not found!");
        departmentRepository.deleteById(id);
        return new ApiResponse(true, "department deleted!");
    }
}
