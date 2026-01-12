package it.prova.gestione_hotel.security;

import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtenteRepository utenteRepository;

    @Autowired
    public CustomUserDetailsService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return new UserDetailsImpl(utente);
    }
}
