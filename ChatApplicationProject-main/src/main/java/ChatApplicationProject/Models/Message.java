package ChatApplicationProject.Models;

import ChatApplicationProject.Configuration.AesEncryptor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = AesEncryptor.class)
    private String content;

    @ManyToOne
    private User user;

    private String username;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Chat chat;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
