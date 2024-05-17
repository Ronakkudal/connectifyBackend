package ChatApplicationProject.requestDto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatRequest {
    private String groupChatName;
    private List<Integer> userIds;


}
