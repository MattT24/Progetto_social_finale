package it.social.restsocial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import it.social.restsocial.entity.Utente;
import it.social.restsocial.repository.UtenteRepository;
import java.util.stream.Collectors;

/**
 * Carica UserDetails mappando i Permessi del Ruolo in GrantedAuthority.
 * Attenzione: i permessi sono derivati da RuoloPermesso.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente u = utenteRepository.findWithAuthoritiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + email));

        var authorities = u.getRuolo().getRuoloPermessi().stream()
                .map(rp -> new SimpleGrantedAuthority(rp.getPermesso().getAlias()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), authorities
        );
    }
}
