package it.social.restsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.social.restsocial.entity.Gruppo;

/** Repository CRUD per Gruppo. */
public interface GruppoRepository extends JpaRepository<Gruppo, Long> { }
