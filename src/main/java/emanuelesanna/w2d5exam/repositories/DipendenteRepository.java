package emanuelesanna.w2d5exam.repositories;

import emanuelesanna.w2d5exam.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    Optional<Dipendente> findByUsername(String username);

    Optional<Dipendente> findByEmail(String email);
}
