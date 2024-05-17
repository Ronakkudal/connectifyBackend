package ChatApplicationProject.services;

import ChatApplicationProject.Models.Message;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.MessageRequest;

import java.util.List;

public interface MessageService {

    public Message sendMessage(MessageRequest messageRequest);

    public List<Message> getAllMessageByChatId(Integer chatId);
}
