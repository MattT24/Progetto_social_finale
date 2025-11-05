package it.social.restsocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.social.restsocial.entity.Ruolo;

/** Repository CRUD per Ruolo. */
public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByAlias(String alias);
}
