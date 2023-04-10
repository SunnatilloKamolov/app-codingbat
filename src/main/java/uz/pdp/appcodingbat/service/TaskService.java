package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Department;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.repository.DepartmentRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * This method returns list of tasks.
     *
     * @return Tasks
     */
    public List<uz.pdp.appcodingbat.entity.Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * This method returns one task by id.
     *
     * @param id
     * @return Task
     */
    public uz.pdp.appcodingbat.entity.Task getTaskById(Integer id) {
        Optional<uz.pdp.appcodingbat.entity.Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    /**
     * This method adds new test.
     * @param taskDto
     * @return ApiResponse
     */
    public ApiResponse addTask(TaskDto taskDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(taskDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ApiResponse(false, "department not found!");

        if (taskRepository.existsByNameAndDepartmentId(taskDto.getName(), taskDto.getDepartmentId()))
            return new ApiResponse(false, "this task already exist!");

        uz.pdp.appcodingbat.entity.Task task =new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setAnswerBody(taskDto.getAnswerBody());
        task.setDepartment(optionalDepartment.get());
        taskRepository.save(task);
        return new ApiResponse(true, "task saved!");
    }

    /**
     * This method edits task by id.
     * @param id
     * @param taskDto
     * @return ApiResponse
     */
    public ApiResponse editeTask(Integer id, TaskDto taskDto) {

        Optional<uz.pdp.appcodingbat.entity.Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty())
            return new ApiResponse(false, "task not found!");

        Optional<Department> optionalDepartment = departmentRepository.findById(taskDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ApiResponse(false, "department not found!");

        if (taskRepository.existsByNameAndDepartmentIdAndIdNot(taskDto.getName(), taskDto.getDepartmentId(), id))
            return new ApiResponse(false, "task already exist!");

        uz.pdp.appcodingbat.entity.Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setAnswerBody(taskDto.getAnswerBody());
        task.setDepartment(optionalDepartment.get());
        taskRepository.save(task);
        return new ApiResponse(false, "task edited!");
    }

    /**
     * This method deletes task by id.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteTask(Integer id) {
        Optional<uz.pdp.appcodingbat.entity.Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty())
            return new ApiResponse(false, "task not found!");
        taskRepository.deleteById(id);
        return new ApiResponse(true, "task deleted!");
    }
}
