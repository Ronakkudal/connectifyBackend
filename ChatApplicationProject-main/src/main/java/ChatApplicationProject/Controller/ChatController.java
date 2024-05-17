
package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.GroupChatRequest;
import ChatApplicationProject.requestDto.UpdateGroupRequest;
import ChatApplicationProject.services.ChatService;
import ChatApplicationProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("chat/createchat")
    public ResponseEntity<Chat> createChat(@RequestParam("username") String username, @RequestHeader("Authorization") String token){
        System.out.println("jwt token received-------------------"+token);
        return new ResponseEntity<>(chatService.createChat(username), HttpStatus.CREATED);
    }

    @GetMapping("/chat/find")
    public ResponseEntity<Chat> findChatById(@RequestParam Integer chatId, @RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chatService.findChatById(chatId),HttpStatus.CREATED);
    }
    @GetMapping("/chat/users")
    public ResponseEntity<Set<User>> findUsersByChatId(@RequestParam Integer chatId, @RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chatService.findUserByChatId(chatId),HttpStatus.OK);
    }

    @PostMapping(value = "chat/creategroup", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chat> createGroup(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String token){


        return new ResponseEntity<>(chatService.createGroup(groupChatRequest),HttpStatus.CREATED);
    }

    @GetMapping("/my/chats")
    public ResponseEntity<List<Chat>> myChats( @RequestParam Integer userId,@RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chatService.findAllChatByUserid(userId),HttpStatus.OK);
    }

    @PutMapping("/chat/removefromgroup")
    public ResponseEntity<Chat> leaveGroup(@RequestBody UpdateGroupRequest updateGroupRequest){

        return new ResponseEntity<>(chatService.leaveGroup(updateGroupRequest),HttpStatus.CREATED);
    }
    @PutMapping("/chat/addtogroup")
    public ResponseEntity<Chat> addToGroup(@RequestBody UpdateGroupRequest updateGroupRequest){
        System.out.println("Inside leage groprrfkgn++++++++++++++++++++++++++++++++++++++++++++++++");

        return new ResponseEntity<>(chatService.addMemberToGroup(updateGroupRequest),HttpStatus.CREATED);
    }
    @PutMapping("/chat/renamegroup")
    public ResponseEntity<Chat> renameGroup(@RequestBody UpdateGroupRequest updateGroupRequest){
        System.out.println("Inside leage groprrfkgn++++++++++++++++++++++++++++++++++++++++++++++++");

        return new ResponseEntity<>(chatService.renameGroup(updateGroupRequest),HttpStatus.CREATED);
    }
    @PutMapping("/chat/makeadmin")
    public ResponseEntity<Chat> makeAdmin(@RequestBody UpdateGroupRequest updateGroupRequest){
        System.out.println("Inside leage groprrfkgn++++++++++++++++++++++++++++++++++++++++++++++++");

        return new ResponseEntity<>(chatService.makeAdmin(updateGroupRequest),HttpStatus.CREATED);
    }


}
