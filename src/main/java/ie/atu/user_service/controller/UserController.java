package ie.atu.user_service.controller;

import ie.atu.user_service.model.User;
import ie.atu.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

    // Constructor Based Dependency Injection
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get Request to Return List of all Users
    @GetMapping("/returnAllUsers")
    public List<User> getUserList() {
        return userService.findAll();
    }

    // Get Request to find by ID Search
    @GetMapping("/callUserService/{userId}")
    public User getUserById(@PathVariable String userId) {
       return userService.getUserById(userId);
    }

    // Post method to create a single user
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User userCreated = userService.create(user);
        return ResponseEntity
                .created(URI.create("/api/user" + userCreated.getUserID()))
                .body(userCreated);
    }

    // Post method to add multiple users
    @PostMapping("/addUsers")
    public ResponseEntity<List<User>> createUsers(@Valid @RequestBody List<User> users) {
        List<User> addedUsers = userService.createUsers(users);
        return ResponseEntity
                .created(URI.create("/api/users"))
                .body(addedUsers);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable String userId, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.update(userId, user));
    }

    // Delete method to Delete details
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}