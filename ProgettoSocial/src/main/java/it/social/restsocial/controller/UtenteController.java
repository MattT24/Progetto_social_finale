package it.social.restsocial.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.social.restsocial.dto.IdRequest;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.UtenteDto;
import it.social.restsocial.dto.UtenteFormDto;
import it.social.restsocial.service.UtenteService;

import java.util.List;

/** API REST per Utenti. */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired private UtenteService service;

    /** Crea utente. */
    @PostMapping
    @PreAuthorize("hasAuthority('UTENTE_CREATE')")
    public ResponseEntity<UtenteDto> create(@Valid @RequestBody UtenteFormDto form) {
    	var create = service.create(form);
        return ResponseEntity.status(201).body(create);
    }

    /** Aggiorna utente (id nel body). */
    @PutMapping
    @PreAuthorize("hasAuthority('UTENTE_UPDATE')")
    public ResponseEntity<UtenteDto> update(@Valid @RequestBody UtenteFormDto form) {
    	var updated = service.update(form);
    	return ResponseEntity.status(201).body(updated);
    }

    /** Elimina utente (id nel body). */
    @DeleteMapping
    @PreAuthorize("hasAuthority('UTENTE_DELETE')")
    public ResponseEntity<Void> delete(@Valid @RequestBody IdRequest req) {
        service.delete(req.getId());
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/profilo")
    @PreAuthorize("hasAuthority('UTENTE_DELETE_PROFILE')")
    public ResponseEntity<Void> deleteMyProfile() {
        service.deleteMyProfile();
        return ResponseEntity.noContent().build();
    }

    //Sospendi utente
    
    /** Dettaglio utente. */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('UTENTE_READ')")
    public ResponseEntity<UtenteDto> get(@PathVariable Long id) {
    	var dto = service.getById(id);
    	return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /** Lista paginata. */
    @GetMapping
    
    public PageResponse<UtenteDto> list(Pageable pageable) {
        return service.list(pageable);
    }

    /** Lista completa (non paginata). */
    @GetMapping("/tutti")
    @PreAuthorize("hasAuthority('UTENTE_READ')")
    public List<UtenteDto> listAll() {
        return service.listAll();
    }

    /** Profilo utente corrente (id dal JWT). */
    @GetMapping("/profilo")
    @PreAuthorize("hasAuthority('UTENTE_PROFILE')")
    public ResponseEntity<UtenteDto> profile() {
    	var dto = service.getProfile();
    	return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
    
    //si potrebbe fare una nuova entità con il numero di like
}