package gestion.incident.incident.materiel;

import gestion.incident.incident.incidents.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    Optional<Materiel> findByNom(String nomMateriel);
}
