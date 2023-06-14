package gestion.incident.incident.procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository <Procedure, Long> {
    Optional<Procedure> findByNom(String nomProcedure);

}
