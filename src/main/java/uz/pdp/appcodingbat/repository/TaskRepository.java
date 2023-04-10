package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    boolean existsByNameAndDepartmentId(String name, Integer department_id);

    boolean existsByNameAndDepartmentIdAndIdNot(String name, Integer department_id, Integer id);

}
