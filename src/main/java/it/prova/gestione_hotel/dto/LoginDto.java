package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {
    @NotBlank
    @Size(min = 3, message = "Lo username deve contenere almeno 3 caratteri")
    private String username;

    @NotBlank
    @Size(min = 5, message = "La password deve contenere almeno 5 caratteri")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
