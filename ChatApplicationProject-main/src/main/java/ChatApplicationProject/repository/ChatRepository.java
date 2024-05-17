package ChatApplicationProject.repository;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

   @Query(" from Chat as c where c.id in :chatIds ")
    public List<Chat> findAllChatByUserId( @Param("chatIds") List<Integer> chatIds);

   @Query("select c from Chat c where c.isgroup=false And :user1 member of c.users And :user2 member of c.users ")
    public  Chat findSingleChatByUserIds(@Param("user1") User user1,@Param("user2") User user2);
}
