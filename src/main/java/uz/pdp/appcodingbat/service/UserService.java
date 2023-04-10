package uz.pdp.appcodingbat.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * This method returns all users.
     *
     * @return Users
     */
    public List<uz.pdp.appcodingbat.entity.User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * This method returns one user by id.
     * @param id
     * @return User
     */
    public User getUserById(Integer id) {
        Optional<uz.pdp.appcodingbat.entity.User> optionalUser = userRepository.findById(id);
        return (User) optionalUser.orElse(null);
    }

    /**
     * This method saves a new user.
     * @param userDto
     * @return ApiResponse
     */
    public ApiResponse addUser(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail()))
            return new ApiResponse(false, "this email already exist!");

        User user = (User) new uz.pdp.appcodingbat.entity.User();
        ((uz.pdp.appcodingbat.entity.User) user).setFirstName(userDto.getFirstName());
        ((uz.pdp.appcodingbat.entity.User) user).setLastName(userDto.getLastName());
        ((uz.pdp.appcodingbat.entity.User) user).setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return new ApiResponse(true, "user saved!");
    }

    /**
     * This method edits user by id.
     * @param userDto
     * @return ApiResponse
     */
    public ApiResponse editeUser(Integer id, UserDto userDto) {

        Optional<uz.pdp.appcodingbat.entity.User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");

        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(), id))
            return new ApiResponse(false, "this email already exist!");

        uz.pdp.appcodingbat.entity.User user =optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse(true, "user edited!");
    }

    /**
     * This method deletes user by id.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteUser(Integer id) {
        Optional<uz.pdp.appcodingbat.entity.User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");
        userRepository.deleteById(id);
        return new ApiResponse(true, "user deleted!");
}
}
