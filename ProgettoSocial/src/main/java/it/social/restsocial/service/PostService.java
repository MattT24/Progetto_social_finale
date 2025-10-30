package it.social.restsocial.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.PostDto;
import it.social.restsocial.dto.PostFormDto;
import it.social.restsocial.entity.Post;
import it.social.restsocial.entity.Utente;
import it.social.restsocial.mapper.DtoMapper;

import it.social.restsocial.repository.PostRepository;
import it.social.restsocial.repository.UtenteRepository;
import jakarta.validation.Valid;

@Service
public class PostService {

	@Autowired private PostRepository repo;
	@Autowired private UtenteRepository utenteRepo;

	@Transactional
	public PostDto create(@Valid PostFormDto form) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
		Post p = new Post();
		p.setId(form.getId());
		p.setContenuto(form.getContenuto());
		p.setUtente(u);
		p.setDataOra(form.getDataOra());
		return DtoMapper.toPostDto(repo.save(p));
	}
	@Transactional
	public PostDto update(@Valid PostFormDto form) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
        if (form.getId() == null) throw new RuntimeException("Id post è obbligatorio per l' update");
		Post p = repo.findById(form.getId()).
				orElseThrow(() -> new RuntimeException("Post non trovato"));
		if (u != p.getUtente()) throw new RuntimeException("L'utente non possiede il post");
		p.setContenuto(form.getContenuto());
        return DtoMapper.toPostDto(repo.save(p));
	}
	
	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);		
	}
	
	@Transactional
	public void deleteMyPost(Long id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    Optional<Post> opt = repo.findById(id);
	    Post p = opt.get();
	    if (u != p.getUtente()) throw new RuntimeException("L'utente non possiede il post");
        if (id == null) throw new RuntimeException("Id post è obbligatorio per il delete");
		repo.deleteById(id);
	}
	@Transactional(readOnly = true)
	public PageResponse<PostDto> listAll(Pageable pageable) {
	//	Page<Like> like = likeRepo.f
		return PageResponse.from(repo.findAll(pageable).map(DtoMapper::toPostDto));
	}
	@Transactional(readOnly = true)
	public PageResponse<PostDto> allPostByUtente(@Valid Long utenteId, Pageable pageable) {
	 //è necessario controllare se l'utente passato è valido? O ritorna semplicemente una lista vuota?
		return PageResponse.from(repo.findByUtenteId(utenteId, pageable).map(DtoMapper::toPostDto));
	}
	
	@Transactional(readOnly = true)
	public PageResponse<PostDto> allMyPost(Pageable pageable){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    return PageResponse.from(repo.findByUtenteId(u.getId(), pageable).map(DtoMapper::toPostDto));	
	}
	
	@Transactional(readOnly = true)
	public PostDto postById(Long id) {       
		return  repo.findById(id).map(DtoMapper::toPostDto).orElseThrow(()-> new RuntimeException("Post non trovato"));
	}
}
