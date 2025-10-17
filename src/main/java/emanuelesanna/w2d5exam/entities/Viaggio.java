package emanuelesanna.w2d5exam.entities;

import emanuelesanna.w2d5exam.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idViaggio;
    private String destinazione;
    private LocalDate data;
    private StatoViaggio stato;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    Dipendente dipendente;

    public Viaggio(String destinazione, LocalDate data, StatoViaggio stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}
