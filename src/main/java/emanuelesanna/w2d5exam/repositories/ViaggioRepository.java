package emanuelesanna.w2d5exam.repositories;

import emanuelesanna.w2d5exam.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
    //    Questa per aiutarmi nel requisito
    List<Viaggio> findByDipendenteIdDipendenteAndData(UUID dipendenteId, LocalDate data);
}
