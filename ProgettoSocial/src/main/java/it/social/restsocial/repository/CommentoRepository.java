package it.social.restsocial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.social.restsocial.entity.Commento;
import jakarta.validation.Valid;


public interface CommentoRepository extends JpaRepository<Commento, Long> {

	Page<Commento> findByUtenteId(@Valid Long utenteId, Pageable pageable);
	Page<Commento> findByPostId(@Valid Long postId, Pageable pageable);

}
