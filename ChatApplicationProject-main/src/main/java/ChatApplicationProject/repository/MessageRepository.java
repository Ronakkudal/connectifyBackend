package ChatApplicationProject.repository;

import ChatApplicationProject.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
