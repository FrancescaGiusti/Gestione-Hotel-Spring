package it.prova.gestione_hotel.security;

import it.prova.gestione_hotel.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final Utente utente;

    public UserDetailsImpl(Utente utente) {
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return utente.getRuoli()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getCodice().name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
