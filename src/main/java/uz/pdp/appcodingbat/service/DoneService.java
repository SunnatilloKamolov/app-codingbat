package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Done;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.DoneDto;
import uz.pdp.appcodingbat.repository.DoneRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoneService {
    @Autowired
    DoneRepository doneRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * This method returns all done.
     * @return all done
     */
    public List<Done> getAll() {
        return doneRepository.findAll();
    }

    /**
     * This method returns one done by id.
     * @return all done
     */
    public Done getById(Integer id) {

        Optional<Done> optionalDone = doneRepository.findById(id);
        return optionalDone.orElse(null);
    }

    /**
     * This method adds done.
     * @param doneDto
     * @return ApiResponse
     */
    public ApiResponse add(DoneDto doneDto) {

        Optional<uz.pdp.appcodingbat.entity.User> optionalUser = userRepository.findById(doneDto.getUserId());
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");

        Optional<uz.pdp.appcodingbat.entity.Task> optionalTask = taskRepository.findById(doneDto.getTaskId());
        if (optionalTask.isEmpty())
            return new ApiResponse(false, "task not found!");

        Done done = new Done();
        done.setUser(optionalUser.get());
        done.setTask(optionalTask.get());
        doneRepository.save(done);

        return new ApiResponse(true,"done saved!");
    }

    /**
     * This method edits done by id.
     * @param id
     * @param doneDto
     * @return ApiResponse
     */
    public ApiResponse edite(Integer id, DoneDto doneDto) {

        Optional<Done> optionalDone = doneRepository.findById(id);
        if (optionalDone.isEmpty())
            return new ApiResponse(false, "done not found!");

        Optional<User> optionalUser = userRepository.findById(doneDto.getUserId());
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");

        Optional<Task> optionalTask = taskRepository.findById(doneDto.getTaskId());
        if (optionalTask.isEmpty())
            return new ApiResponse(false, "task not found!");

        Done done = optionalDone.get();
        done.setUser(optionalUser.get());
        done.setTask(optionalTask.get());
        doneRepository.save(done);

        return new ApiResponse(true,"done edited!");
    }

    /**
     * This method deletes done by id.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse delete(Integer id) {
        Optional<Done> optionalDone = doneRepository.findById(id);
        if (optionalDone.isEmpty())
            return new ApiResponse(false, "done not found!");
        doneRepository.deleteById(id);
        return new ApiResponse(true, "done deleted!");
    }
}
