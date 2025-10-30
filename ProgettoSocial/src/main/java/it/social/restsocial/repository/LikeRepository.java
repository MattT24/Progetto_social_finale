package it.social.restsocial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import it.social.restsocial.entity.Like;
import jakarta.validation.Valid;

public interface LikeRepository extends JpaRepository<Like, Long>{

	Page<Like> findByPostId(@Valid Long postId, Pageable pageable);

	Page<Like> findAllByUtenteId(Long id, Pageable pageable);


}
