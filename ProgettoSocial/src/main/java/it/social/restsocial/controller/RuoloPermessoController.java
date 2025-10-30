package it.social.restsocial.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.social.restsocial.dto.PermessoDto;
import it.social.restsocial.dto.RuoloDto;
import it.social.restsocial.dto.RuoloPermessoRequest;
import it.social.restsocial.service.RuoloPermessoService;

import java.util.List;

/**
 * API REST per entità di join RuoloPermesso:
 * - create = associa permesso a ruolo;
 * - delete = dissocia permesso da ruolo;
 * - read lista permessi di un ruolo (non paginata).
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ruoli-permessi")
public class RuoloPermessoController {

    @Autowired private RuoloPermessoService service;

    /** Restituisce tutti i permessi di un ruolo (non paginato). */
    @GetMapping("/ruolo/{ruoloId}")
    @PreAuthorize("hasAuthority('RUOLO_READ')")
    public List<PermessoDto> listByRuolo(@PathVariable Long ruoloId) {
        return service.listPermessiByRuolo(ruoloId);
    }

    /** Crea l'associazione ruolo-permesso. */
    @PostMapping
    @PreAuthorize("hasAuthority('RUOLO_UPDATE')")
    public ResponseEntity<RuoloDto> create(@Valid @RequestBody RuoloPermessoRequest req) {
    	var create = service.creaAssociazione(req);
        return ResponseEntity.status(201).body(create);
    }

    /** Elimina l'associazione ruolo-permesso. */
    @DeleteMapping
    @PreAuthorize("hasAuthority('RUOLO_UPDATE')")
    public ResponseEntity<RuoloDto> delete(@Valid @RequestBody RuoloPermessoRequest req) {
    	var create = service.eliminaAssociazione(req);
        return ResponseEntity.status(201).body(create);
    }
}
