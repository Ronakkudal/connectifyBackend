package ChatApplicationProject.services;

import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {

    public User save(User user);

    public User register(UserRequest userRequest) throws Exception;

    public User findById(Integer userid2);

    public User findByUsername(String Username);

    public User getUser();

    public List<User> searchUser(String search);
}
