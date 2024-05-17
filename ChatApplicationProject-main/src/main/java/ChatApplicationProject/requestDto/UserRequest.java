package ChatApplicationProject.requestDto;

import ChatApplicationProject.Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @NotBlank(message = " name cannot be empty")
    @Size(min=3,max=12,message = "name should be between 3 to 12")
    private String name;

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "password cannot be blank")
    @Size(min=6,message="password should be of minimum 6 characters")
    private String password;


    private String imageurl;


    public User toUser(){
        User user=new User();
        user.setName(this.getName());
        user.setUsername(this.getUsername());
        user.setPassword(passwordEncoder.encode(this.getPassword()));
        user.setImageurl(this.getImageurl());
        return user;
    }
}
