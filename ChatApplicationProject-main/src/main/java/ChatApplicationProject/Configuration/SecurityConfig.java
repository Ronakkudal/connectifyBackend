package ChatApplicationProject.Configuration;

import ChatApplicationProject.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        @Autowired
        private JwtAutheticationEntryPoint point;
        @Autowired
        private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth-> auth.requestMatchers("/login").permitAll()
                            .requestMatchers("/register","/ws/**","/").permitAll()
                            .anyRequest()
                            .authenticated())
                    .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(daoAuthenticationProvider())
                    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
