package ChatApplicationProject.Models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {

    private String token;
    private String username;
    private int id;
    private String name;
    private String imageurl;
    private Set<Integer> ChatIdList;
    private LocalDateTime creationDate;


}
