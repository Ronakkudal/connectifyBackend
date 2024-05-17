package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.GroupChatRequest;
import ChatApplicationProject.requestDto.UpdateGroupRequest;

import java.util.List;
import java.util.Set;

public interface ChatService {

    public Chat createChat(String username) ;

    public Chat findChatByName(String name) ;

    public List<Chat> findAllChatByUserid(Integer userId);
    public Set<User> findUserByChatId(Integer chatId);

    public Chat createGroup(GroupChatRequest groupChatRequest) ;


    public Chat addMemberToGroup(UpdateGroupRequest updateGroupRequest) ;

    public Chat findChatById(Integer chatId);

    public Chat leaveGroup(UpdateGroupRequest updateGroupRequest);

    public Chat renameGroup(UpdateGroupRequest updateGroupRequest);

    public  Chat makeAdmin(UpdateGroupRequest updateGroupRequest);
}
