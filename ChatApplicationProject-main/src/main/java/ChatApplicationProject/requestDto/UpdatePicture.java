package ChatApplicationProject.requestDto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePicture {

   private Integer userid;
   private String imageurl;
}
