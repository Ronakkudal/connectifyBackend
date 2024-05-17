package ChatApplicationProject.requestDto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupRequest {
    private  Integer chatId;
    private  Integer userId;
    private  String  groupChatName;
}