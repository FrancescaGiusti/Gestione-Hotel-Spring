package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Codice;
import it.prova.gestione_hotel.model.Ruolo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RuoloDto {
    private Long id;
    @NotNull
    private Codice codice;
    @NotBlank
    private String descrizione;
    private Set<UtenteDto> utenti = new HashSet<>();

    public RuoloDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Codice getCodice() {
        return codice;
    }

    public void setCodice(Codice codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<UtenteDto> getUtenti() {
        return utenti;
    }

    public void setUtenti(Set<UtenteDto> utenti) {
        this.utenti = utenti;
    }

    public static RuoloDto fromModel(Ruolo ruolo) {
        RuoloDto ruoloDto = new RuoloDto();
        ruoloDto.setCodice(ruolo.getCodice());
        ruoloDto.setDescrizione(ruolo.getDescrizione());
        ruoloDto.setUtenti(UtenteDto.fromModelLight(ruolo.getUtenti()));
        ruoloDto.setId(ruolo.getId());
        return ruoloDto;
    }

    public static Set<RuoloDto> fromModel(Set<Ruolo> ruolo){
        return ruolo.stream().map(r -> RuoloDto.fromModel(r)).collect(Collectors.toSet());
    }

    public Ruolo toModel(){
        Ruolo ruolo = new Ruolo();
        ruolo.setCodice(this.getCodice());
        ruolo.setDescrizione(this.getDescrizione());
        ruolo.setId(this.getId());
        ruolo.setUtenti((this.getUtenti().stream().map(u-> u.toModel()).collect(Collectors.toSet())));
        return ruolo;
    }

    public static RuoloDto fromModelLight(Ruolo ruolo) {
        RuoloDto ruoloDto = new RuoloDto();
        ruoloDto.setCodice(ruolo.getCodice());
        ruoloDto.setDescrizione(ruolo.getDescrizione());
        ruoloDto.setId(ruolo.getId());
        return ruoloDto;
    }

    public static Set<RuoloDto> fromModelLight(Set<Ruolo> ruolo){
        return ruolo.stream().map(r -> RuoloDto.fromModelLight(r)).collect(Collectors.toSet());
    }
}
