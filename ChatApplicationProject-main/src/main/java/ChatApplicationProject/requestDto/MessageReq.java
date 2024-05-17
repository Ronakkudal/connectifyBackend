package ChatApplicationProject.requestDto;

import ChatApplicationProject.Models.Message;
import ChatApplicationProject.Models.User;
import lombok.*;

import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReq {
    private Message message;

    private Integer chatId;





}
