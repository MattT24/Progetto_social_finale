package it.social.restsocial.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.social.restsocial.dto.LikeDto;
import it.social.restsocial.dto.LikeFormDto;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.PostDto;
import it.social.restsocial.entity.Commento;
import it.social.restsocial.entity.Like;
import it.social.restsocial.entity.Post;
import it.social.restsocial.entity.Utente;
import it.social.restsocial.mapper.DtoMapper;
import it.social.restsocial.repository.LikeRepository;
import it.social.restsocial.repository.PostRepository;
import it.social.restsocial.repository.UtenteRepository;

import jakarta.validation.Valid;

@Service
public class LikeService {

	@Autowired private LikeRepository repo;
	@Autowired private UtenteRepository utenteRepo;
	@Autowired private PostRepository postRepo;

	@Transactional
	public LikeDto create(@Valid LikeFormDto form) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
		Optional<Post> po = postRepo.findById(form.getPost().getId());
		Post p= po.get();
		Like l = new Like();
		l.setId(form.getId());
		l.setPost(p);
		l.setUtente(u);
		l.setDataOra(form.getDataOra());
		return DtoMapper.toLikeDto(repo.save(l));
	}
	@Transactional
	public void deleteMyLike(Long id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    if (id == null) throw new RuntimeException("Id like è obbligatorio per il delete");
	    Optional<Like> opt = repo.findById(id);
	    Like l = opt.get();
	    if (u != l.getUtente()) throw new RuntimeException("L'utente non possiede il like");
		repo.deleteById(id);
	}
	

	@Transactional(readOnly = true)
	public PageResponse<LikeDto> allLikeByPost(@Valid Long postId, Pageable pageable) {

		return PageResponse.from(repo.findByPostId(postId, pageable).map(DtoMapper::toLikeDtoLight));
	}
	@Transactional
	public PageResponse<LikeDto> allPostByMyLike(Pageable pageable) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    Page<Like> like = repo.findAllByUtenteId(u.getId(), pageable);
		return PageResponse.from(like.map(DtoMapper::toLikeDto));
	}
	
	
}
