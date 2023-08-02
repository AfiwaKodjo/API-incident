package gestion.incident.incident.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
    select t from Token t inner join Utilisateur u on t.utilisateur.id = u.id 
    where u.id= :utilId and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokensByUser(Integer utilId);

    Optional<Token> findByToken(String token);
}
