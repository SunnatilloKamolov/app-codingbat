package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.ProgrammingLanguage;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
