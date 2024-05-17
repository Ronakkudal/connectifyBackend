package ChatApplicationProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String chatname;

    private String chatimage;

    private Boolean isgroup;

    private Set<Integer> admins=new HashSet<>();
    @OneToOne
    private Message latestMessage;
    private String createdby;
    @ManyToMany
    private Set<User> users=new HashSet<>();

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private List<Message> messages =new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime creationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id && Objects.equals(chatname, chat.chatname) && Objects.equals(chatimage, chat.chatimage) && Objects.equals(isgroup, chat.isgroup) && Objects.equals(admins, chat.admins) && Objects.equals(latestMessage, chat.latestMessage) && Objects.equals(createdby, chat.createdby) && Objects.equals(users, chat.users) && Objects.equals(messages, chat.messages) && Objects.equals(creationDate, chat.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatname, chatimage, isgroup, admins, latestMessage, createdby, users, messages, creationDate);
    }


}
