package it.social.restsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.social.restsocial.entity.Permesso;

import java.util.List;

/** Repository CRUD per Permesso. */
public interface PermessoRepository extends JpaRepository<Permesso, Long> {
    List<Permesso> findByGruppo_Id(Long gruppoId);
}
