package ChatApplicationProject.services;

import ChatApplicationProject.Exception.UserNotFoundException;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.repository.UserRepository;
import ChatApplicationProject.requestDto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user){
      return  userRepository.save(user);
    }
    @Override
    public User register(UserRequest userRequest) throws Exception {
        User newUser=userRequest.toUser();
        return userRepository.save(newUser);
    }

    @Override
    public User findById(Integer userid) {
        Optional<User> user=userRepository.findById(userid);
        return user.get();
    }

    @Override
    public User getUser() {
      String username= SecurityContextHolder.getContext().getAuthentication().getName();
       return  userRepository.findByUsername(username).get();
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username).get();

    }
    public List<User> searchUser(String search){
       return userRepository.findByUsernameContaining(search);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException());
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserImage(Integer userId, String imageurl) {
        User user=this.findById(userId);
        user.setImageurl(imageurl);
       return userRepository.save(user);
    }
}
