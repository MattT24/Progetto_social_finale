package it.social.restsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.RuoloDto;
import it.social.restsocial.dto.RuoloFormDto;
import it.social.restsocial.dto.RuoloUtenteRequest;
import it.social.restsocial.dto.UtenteDto;
import it.social.restsocial.entity.Ruolo;
import it.social.restsocial.entity.Utente;
import it.social.restsocial.mapper.DtoMapper;
import it.social.restsocial.repository.RuoloRepository;
import it.social.restsocial.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Logica di business per Ruoli (CRUD; join gestite da RuoloPermessoService). */
@Service
public class RuoloService {

    @Autowired private RuoloRepository repo;
    @Autowired private UtenteRepository utenteRepo;

    /** Crea ruolo (senza permessi). */
    @Transactional
    public RuoloDto create(RuoloFormDto form) {
        Ruolo r = new Ruolo();
        r.setNome(form.getNome());
        r.setAlias(form.getAlias());
        return DtoMapper.toRuoloDtoLight(repo.save(r));
    }

    /** Aggiorna ruolo (nome/alias). */
    @Transactional
    public RuoloDto update(RuoloFormDto form) {
        if (form.getId() == null) throw new RuntimeException("Id ruolo obbligatorio per update");
        Ruolo r = repo.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        r.setNome(form.getNome());
        r.setAlias(form.getAlias());
        return DtoMapper.toRuoloDtoLight(repo.save(r));
    }

    /** Elimina ruolo. */
    @Transactional
    public void delete(Long id) { repo.deleteById(id); }

    /** Dettaglio ruolo: include associazioni RuoloPermesso (nested light). */
    @Transactional(readOnly = true)
    public RuoloDto getById(Long id) {
        Ruolo r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        return DtoMapper.toRuoloDtoWithAssoc(r);
    }

    /** Lista paginata ruoli (light). */
    @Transactional(readOnly = true)
    public PageResponse<RuoloDto> list(Pageable pageable) {
        return PageResponse.from(repo.findAll(pageable).map(DtoMapper::toRuoloDtoLight));
    }

    /** Lista completa non paginata (light). */
    @Transactional(readOnly = true)
    public List<RuoloDto> listAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toRuoloDtoLight)
                .collect(Collectors.toList());
    }
    
    /** Associa ruolo a utente */
    @Transactional
    public UtenteDto updateRuoloUtente(RuoloUtenteRequest req) {
        Optional<Ruolo> optR = repo.findById(req.getRuoloId());
        Ruolo r = optR.get();
        Optional<Utente> optU = utenteRepo.findById(req.getUtenteId());
        Utente u = optU.get();
    	u.setRuolo(r); 
        utenteRepo.save(u);
        return DtoMapper.toUtenteDto(u); 
    }
}
