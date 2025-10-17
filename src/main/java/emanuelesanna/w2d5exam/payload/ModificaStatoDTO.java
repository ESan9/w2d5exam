package emanuelesanna.w2d5exam.payload;

import emanuelesanna.w2d5exam.enums.StatoViaggio;
import jakarta.validation.constraints.NotNull;

public record ModificaStatoDTO(
        @NotNull(message = "Il nuovo stato è obbligatorio.")
        StatoViaggio nuovoStato) {
}
