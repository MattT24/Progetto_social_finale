package it.social.restsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.PermessoDto;
import it.social.restsocial.dto.PermessoFormDto;
import it.social.restsocial.entity.Gruppo;
import it.social.restsocial.entity.Permesso;
import it.social.restsocial.mapper.DtoMapper;
import it.social.restsocial.repository.GruppoRepository;
import it.social.restsocial.repository.PermessoRepository;

import java.util.List;
import java.util.stream.Collectors;

/** Logica di business per Permessi. */
@Service
public class PermessoService {

    @Autowired private PermessoRepository repo;
    @Autowired private GruppoRepository gruppoRepo;

    /** Crea permesso (gruppo opzionale). */
    @Transactional
    public PermessoDto create(PermessoFormDto form) {
        Permesso p = new Permesso();
        p.setNome(form.getNome());
        p.setAlias(form.getAlias());
        if (form.getGruppo() != null && form.getGruppo().getId() != null) {
            Gruppo g = gruppoRepo.findById(form.getGruppo().getId())
                    .orElseThrow(() -> new RuntimeException("Gruppo non trovato"));
            p.setGruppo(g);
        }
        return DtoMapper.toPermessoDtoLight(repo.save(p));
    }

    /** Aggiorna permesso (nome, alias, gruppo). */
    @Transactional
    public PermessoDto update(PermessoFormDto form) {
        if (form.getId() == null) throw new RuntimeException("Id permesso obbligatorio per update");
        Permesso p = repo.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("Permesso non trovato"));
        p.setNome(form.getNome());
        p.setAlias(form.getAlias());
        if (form.getGruppo() != null) {
            if (form.getGruppo().getId() == null) {
                p.setGruppo(null);
            } else {
                Gruppo g = gruppoRepo.findById(form.getGruppo().getId())
                        .orElseThrow(() -> new RuntimeException("Gruppo non trovato"));
                p.setGruppo(g);
            }
        }
        return DtoMapper.toPermessoDtoLight(repo.save(p));
    }

    /** Elimina permesso. */
    @Transactional
    public void delete(Long id) { repo.deleteById(id); }

    /** Dettaglio permesso (light; se vuoi anche le associazioni usa toPermessoDtoWithAssoc). */
    @Transactional(readOnly = true)
    public PermessoDto getById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toPermessoDtoLight)
                .orElseThrow(() -> new RuntimeException("Permesso non trovato"));
    }

    /** Lista paginata (light). */
    @Transactional(readOnly = true)
    public PageResponse<PermessoDto> list(Pageable pageable) {
        return PageResponse.from(repo.findAll(pageable).map(DtoMapper::toPermessoDtoLight));
    }

    /** Lista completa non paginata (light). */
    @Transactional(readOnly = true)
    public List<PermessoDto> listAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toPermessoDtoLight)
                .collect(Collectors.toList());
    }
}
