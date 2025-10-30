package it.social.restsocial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.social.restsocial.entity.Post;
import jakarta.validation.Valid;

public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByUtenteId(@Valid Long utenteId, Pageable pageable);

}
