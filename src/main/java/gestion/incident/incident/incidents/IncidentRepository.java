package gestion.incident.incident.incidents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findByNomIncident(String nomIncident);
}
