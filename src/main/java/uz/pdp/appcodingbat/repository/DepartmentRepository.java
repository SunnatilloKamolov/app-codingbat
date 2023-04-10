package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByNameAndProgrammingLanguageId(String name, Integer programmingLanguage_id);

    boolean existsByNameAndProgrammingLanguageIdAndIdNot(String name, Integer programmingLanguage_id, Integer id);
}
