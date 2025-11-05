package it.social.restsocial.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.social.restsocial.dto.CommentoDto;
import it.social.restsocial.dto.CommentoFormDto;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.entity.Commento;
import it.social.restsocial.entity.Utente;
import it.social.restsocial.mapper.DtoMapper;
import it.social.restsocial.repository.CommentoRepository;
import it.social.restsocial.repository.UtenteRepository;
import jakarta.validation.Valid;

@Service
public class CommentoService {

	@Autowired private CommentoRepository repo;
	@Autowired private UtenteRepository utenteRepo;
	
	/*ciao*/
	
	@Transactional
	public CommentoDto create(@Valid CommentoFormDto form) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
		Commento c = new Commento();
		c.setId(form.getId());
		c.setPost(DtoMapper.toPost(form.getPost()));
		c.setUtente(u);
		c.setDataOra(form.getDataOra());
		c.setTesto(form.getTesto());
		return DtoMapper.toCommentoDto(repo.save(c));
	}
	@Transactional
	public CommentoDto update(@Valid CommentoFormDto form) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
		if (form.getId() == null) throw new RuntimeException("Id Commento è obbligatorio per l' update");
		Commento c = repo.findById(form.getId()).
				orElseThrow(() -> new RuntimeException("Commento non trovato"));
		if (u != c.getUtente()) throw new RuntimeException("L'utente non possiede il Commento");
		c.setTesto(form.getTesto());
        return DtoMapper.toCommentoDto(repo.save(c));
	}
	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);		
	}
	@Transactional
	public void deleteMyCommento(Long id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    if (id == null) throw new RuntimeException("Id commento è obbligatorio per il delete");
	    Optional<Commento> opt = repo.findById(id);
	    Commento c = opt.get();
	    if (u != c.getUtente()) throw new RuntimeException("L'utente non possiede il commento");
		repo.deleteById(id);
	}
	@Transactional(readOnly = true)
	public PageResponse<CommentoDto> allCommentiByPost(@Valid Long postId, Pageable pageable) {
		return PageResponse.from(repo.findByPostId(postId, pageable).map(DtoMapper::toCommentoDto));
	}
	@Transactional(readOnly = true)
	public PageResponse<CommentoDto> allCommentiByUtente(@Valid Long utenteId, Pageable pageable) {
		 //è necessario controllare se l'utente passato è valido? O ritorna semplicemente una lista vuota?
		return PageResponse.from(repo.findByUtenteId(utenteId, pageable).map(DtoMapper::toCommentoDto));
	}
	@Transactional(readOnly = true)
	public PageResponse<CommentoDto> allMyCommenti(Pageable pageable){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Utente u = utenteRepo.findByEmail(email)
	    			.orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
	    return PageResponse.from(repo.findByUtenteId(u.getId(), pageable).map(DtoMapper::toCommentoDto));	
	}
	
	@Transactional(readOnly = true)
	public CommentoDto commentoById(Long id) {
		return repo.findById(id).map(DtoMapper::toCommentoDto).orElseThrow(() -> new RuntimeException("Commento non trovato"));
	}
	
}
