package ie.atu.user_service.service;

import ie.atu.user_service.model.User;
import ie.atu.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createAndVerify() {
        User user = new User(null,"1","mike@gmail.com", "1234");
        User savedUser = new User(1L,"1","mike@gmail.com", "1234");

        when(userRepository.save(user)).thenReturn(savedUser);
        User result = userService.create(user);
        assertEquals("mike@gmail.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    /*
    @Test
    void duplicatedThrows() {
        userService.create(User.builder()
                .userID("U2")
                .email("rob@atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
                userService.create(User.builder()
                        .userID("U2")
                        .email("rob@ex.ie")
                        .build()));
    }

    @Test
    void updateUserSuccess(){
        User oldUser = User.builder()
                .userID("U001")
                .email("meike@gmail.com")
                .password("suprsecure")
                .build();
        userService.create(oldUser);

        User newUser = User.builder()
                .userID("U001")
                .email("notMeike@gmail.com")
                .password("1234")
                .build();
        User updating = userService.update(newUser);
        assertEquals(newUser.getEmail(), updating.getEmail());
    }

    @Test
    void deleteUserSuccess(){
        User oldUser = User.builder()
                .userID("U001")
                .email("meike@gmail.com")
                .password("suprsecure")
                .build();
        userService.create(oldUser);
        userService.deleteUser(oldUser.getUserID());
        Optional<User> found = userService.getUserById(oldUser.getUserID());
        assertTrue(found.isEmpty());
    }
    */
}