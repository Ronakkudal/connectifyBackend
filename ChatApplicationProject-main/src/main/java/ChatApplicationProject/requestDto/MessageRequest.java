package ChatApplicationProject.requestDto;

import ChatApplicationProject.Models.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotBlank
    private String content;
    @NotBlank
    private Integer chatId;


}
