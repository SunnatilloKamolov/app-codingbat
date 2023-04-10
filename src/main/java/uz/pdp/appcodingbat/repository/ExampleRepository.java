package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Example;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
}
