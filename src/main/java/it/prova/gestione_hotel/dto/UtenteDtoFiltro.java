package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UtenteDtoFiltro {
    @NotBlank
    @Size(min = 3, message = "Il cognome deve contenere almeno 3 caratteri")
    private String nome;
    @NotBlank
    @Size(min = 3, message = "Il cognome deve contenere almeno 3 caratteri")
    private String cognome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
