package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.JwtRequest;
import ChatApplicationProject.Models.JwtResponse;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.UserRequest;
import ChatApplicationProject.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JwtAuthController {

    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){

        return new ResponseEntity<>(jwtService.login(jwtRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody UserRequest userRequest) throws Exception{

        return new ResponseEntity<>(jwtService.register(userRequest), HttpStatus.CREATED);
    }

}
