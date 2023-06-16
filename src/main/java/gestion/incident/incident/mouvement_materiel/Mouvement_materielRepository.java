package gestion.incident.incident.mouvement_materiel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Mouvement_materielRepository extends JpaRepository<Mouvement_materiel, Long> {
    Optional<Mouvement_materiel>findByNom(String libelleMouvement_materiel);
}
