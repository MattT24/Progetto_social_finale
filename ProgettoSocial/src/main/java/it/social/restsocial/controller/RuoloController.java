package it.social.restsocial.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import it.social.restsocial.dto.IdRequest;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.RuoloDto;
import it.social.restsocial.dto.RuoloFormDto;
import it.social.restsocial.dto.RuoloUtenteRequest;
import it.social.restsocial.dto.UtenteDto;
import it.social.restsocial.service.RuoloService;

import java.util.List;

/** API REST per Ruoli (CRUD; associazioni gestite in RuoloPermessoController). */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {

    @Autowired private RuoloService service;

    /** Crea ruolo. */
    @PostMapping
    @PreAuthorize("hasAuthority('RUOLO_CREATE')")
    public ResponseEntity<RuoloDto> create(@Valid @RequestBody RuoloFormDto form) {
    	var create = service.create(form);
        return ResponseEntity.status(201).body(create);
    }

    /** Aggiorna ruolo (id nel body). */
    @PutMapping
    @PreAuthorize("hasAuthority('RUOLO_UPDATE')")
    public ResponseEntity<RuoloDto> update(@Valid @RequestBody RuoloFormDto form) {
    	var updated = service.update(form);
        return ResponseEntity.status(201).body(updated);
    }

    /** Elimina ruolo (id nel body). */
    @DeleteMapping
    @PreAuthorize("hasAuthority('RUOLO_DELETE')")
    public ResponseEntity<Void> delete(@Valid @RequestBody IdRequest req) {
        service.delete(req.getId());
        return ResponseEntity.noContent().build();
    }

    /** Dettaglio ruolo. */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RUOLO_READ')")
    public ResponseEntity<RuoloDto> get(@PathVariable Long id) {
    	var dto = service.getById(id);
        return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /** Lista paginata ruoli. */
    @GetMapping
    @PreAuthorize("hasAuthority('RUOLO_READ')")
    public PageResponse<RuoloDto> list(Pageable pageable) {
        return service.list(pageable);
    }

    /** Lista completa (non paginata). */
    @GetMapping("/tutti")
    @PreAuthorize("hasAuthority('RUOLO_READ')")
    public List<RuoloDto> listAll() {
        return service.listAll();
    }
    
    @PutMapping("/utente")
    @PreAuthorize("hasAuthority('RUOLO_UPDATE')")
    public ResponseEntity<UtenteDto> updateRuolo(@Valid @RequestBody RuoloUtenteRequest req) {
    	var updated = service.updateRuoloUtente(req);
        return ResponseEntity.status(201).body(updated);
    }
    
}
