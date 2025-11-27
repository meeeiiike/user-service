package ie.atu.user_service.service;

import ie.atu.user_service.errorHandling.DuplicateExceptionHandling;
import ie.atu.user_service.errorHandling.NotFoundException;
import ie.atu.user_service.model.User;
import ie.atu.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User create(User user){
        if(userRepository.findByUserID(user.getUserID()).isPresent()) {
            throw new DuplicateExceptionHandling(user.getUserID() + " already exists D:");
        }
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getUserById(String userId){
        return userRepository.findByUserID(userId).orElseThrow(() -> new NotFoundException(userId + " not found"));
    }

    // Create Multiple Users
    public List<User> createUsers(List<User> userList){
        for(User user : userList){
            if(userRepository.findByUserID(user.getUserID()).isPresent()){
                throw new DuplicateExceptionHandling(user.getUserID() + " already exists D:");
            }
        }
        return userRepository.saveAll(userList);
    }

    // Update User
    public User update(String userId, User user) {
        User updating = userRepository.findByUserID(userId).orElseThrow(() -> new NotFoundException(user.getUserID() + " doesnt Exist"));
        updating.setPassword(user.getPassword());
        updating.setEmail(user.getEmail());
        return userRepository.save(updating);
    }

    // Delete User
    public void deleteUser(String userId) {
        User deleting = userRepository.findByUserID(userId).orElseThrow(() -> new NotFoundException(userId + " doesnt Exist"));
        userRepository.delete(deleting);
    }

}