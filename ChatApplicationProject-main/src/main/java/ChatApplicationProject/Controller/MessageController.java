package ChatApplicationProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import ChatApplicationProject.Models.Message;
import ChatApplicationProject.requestDto.MessageReq;
import ChatApplicationProject.requestDto.MessageRequest;
import ChatApplicationProject.services.ChatService;
import ChatApplicationProject.services.MessageService;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;


    @MessageMapping("/private/{chatId}")
    @SendTo("/specific/private/{chatId}")
    public MessageReq sendToSpecificUser(@Payload MessageReq message){

        return message;
    }

    @PostMapping("/sendmessage")
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest,@RequestHeader("Authorization") String jwtToken){

        return new ResponseEntity<>(messageService.sendMessage(messageRequest), HttpStatus.CREATED);
    }

    @GetMapping("/chat/messages/{chatId}")
    @ResponseBody
    public ResponseEntity<List<Message>> sendMessage(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwtToken){
        return new ResponseEntity<>(messageService.getAllMessageByChatId(chatId),HttpStatus.OK);
    }

}
