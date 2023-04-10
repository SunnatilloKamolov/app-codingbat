package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Done;

public interface DoneRepository extends JpaRepository<Done,Integer> {
    boolean existsByUserIdAndTaskId(Integer user_id, Integer task_id);
}
