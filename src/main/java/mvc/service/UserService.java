package mvc.service;

import mvc.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);

    void editUser(User user);

    void deleteUser(long id);

    User getUserById(Long id);

    List<User> listUsers();

    User getUserByUsername(String username);
}