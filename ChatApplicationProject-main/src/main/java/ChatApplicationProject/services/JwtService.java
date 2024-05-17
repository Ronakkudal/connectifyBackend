package ChatApplicationProject.services;

import ChatApplicationProject.Configuration.MyConfig;
import ChatApplicationProject.Models.JwtRequest;
import ChatApplicationProject.Models.JwtResponse;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.UserRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Autowired
    private MyConfig myconfig;
    @Autowired
    private UserServiceImpl userServiceImpl;

    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    private Key getSigninKey(){
        byte[] key= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }
    public String getToken(UserDetails userDetails){
        Date jwtIssuedAt = new Date(System.currentTimeMillis());
        Date jwtExpiredAt = new Date(System.currentTimeMillis() + myconfig.getAccessTokenMaxLife());

        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(jwtIssuedAt)
                .setExpiration(jwtExpiredAt)
//                .signWith(  new SecretKeySpec(Base64.getDecoder().decode(myconfig.getSecretKey()),
//                        SignatureAlgorithm.HS512.getValue()), SignatureAlgorithm.HS512)
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            myconfig.authenticationManager(new AuthenticationConfiguration()).authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        } catch (Exception e) {
            throw new RuntimeException(e+" authentication manager kaam nhi kiya    ");
        }

    }

    public JwtResponse login(JwtRequest jwtRequest) {

        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        UserDetails userDetails = userServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.getToken(userDetails);

        User user=userServiceImpl.findByUsername(jwtRequest.getUsername());

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .name(user.getName())
                .id(user.getId())
                .username(user.getUsername())
                .imageurl(user.getImageurl())
                .ChatIdList(user.getChatIdList())
                .creationDate(user.getCreationDate())
                 .build();
        return response;
    }

    public JwtResponse register(UserRequest userRequest) throws Exception {
      User newUser= userServiceImpl.register(userRequest);
        UserDetails userDetails = userServiceImpl.loadUserByUsername(newUser.getUsername());
        String token = this.getToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .token(token)
                .name(newUser.getName())
                .id(newUser.getId())
                .username(newUser.getUsername())
                .imageurl(newUser.getImageurl())
                .ChatIdList(newUser.getChatIdList())
                .creationDate(newUser.getCreationDate())
                .build();
        return response;
    }
}

