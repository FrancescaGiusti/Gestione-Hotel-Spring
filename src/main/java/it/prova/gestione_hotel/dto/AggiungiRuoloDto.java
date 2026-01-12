package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Codice;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public class AggiungiRuoloDto {
    @NotNull
    private Codice codice;

    public Codice getCodice() {
        return codice;
    }

    public void setCodice(Codice codice) {
        this.codice = codice;
    }
}
