package ChatApplicationProject.Models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {

    private String username;
    private String password;
}
