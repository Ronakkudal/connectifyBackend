package ChatApplicationProject.repository;

import ChatApplicationProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("from User u where u.username= :username")
    public Optional<User> findByUsername(@Param("username") String username);

    public List<User> findByUsernameContaining(String keyword);
}
