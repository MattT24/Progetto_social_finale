package it.social.restsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.social.restsocial.dto.CommentoDto;
import it.social.restsocial.dto.CommentoFormDto;
import it.social.restsocial.dto.IdRequest;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.service.CommentoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/commenti")
public class CommentoController {

	@Autowired private CommentoService service;
	
	 @PostMapping
	 @PreAuthorize("hasAuthority('COMMENTO_CREATE')")
	 public ResponseEntity<CommentoDto> create(@Valid @RequestBody CommentoFormDto dto) { 
		 var create = service.create(dto);
		 return ResponseEntity.status(201).body(create);
	 }
	 
	 @PutMapping
	 @PreAuthorize("hasAuthority('COMMENTO_UPDATE')")
	 public ResponseEntity<CommentoDto> update (@Valid @RequestBody CommentoFormDto dto) {  //funziona, ma passo troppi dati
		 var updated = service.update(dto);
		 return ResponseEntity.status(201).body(updated);
	 }
	 
	 @DeleteMapping
	 @PreAuthorize("hasAuthority('COMMENTO_DELETE')")
	 public ResponseEntity<Void> delete(@RequestBody IdRequest req){
		 service.delete(req.getId());
		 return ResponseEntity.noContent().build();
	 }
	 
	 @DeleteMapping("/mio")
	 @PreAuthorize("hasAuthority('COMMENTO_MY_DELETE')")
	 public ResponseEntity<Void> deleteMyCommento(@RequestBody IdRequest req){
		 service.deleteMyCommento(req.getId());
		 return ResponseEntity.noContent().build();
	 }
	 
	 @GetMapping
	 @PreAuthorize("hasAuthority('COMMENTO_READ')")
	 public PageResponse<CommentoDto> allCommentiByPost(@RequestBody IdRequest req, Pageable pageable){  //Post o id?
		 return service.allCommentiByPost(req.getId(), pageable);
	 }
	 
	 @GetMapping("/utente")
	 @PreAuthorize("hasAuthority('COMMENTO_READ')")
	 public PageResponse<CommentoDto> allCommentoByUtente(@RequestBody IdRequest req, Pageable pageable){ //Utente o id?
		 return service.allCommentiByUtente(req.getId(), pageable); 
	 }
	 @GetMapping("/miei")
	 @PreAuthorize("hasAuthority('COMMENTO_READ')")
	 public PageResponse<CommentoDto> allMyCommenti(Pageable pageable){
		 return service.allMyCommenti(pageable); 
	 }
	 
	 @GetMapping("/id")
	 @PreAuthorize("hasAuthority('COMMENTO_READ')")
	 public ResponseEntity<CommentoDto> commentoById(@RequestBody IdRequest req) {
		 var dto = service.commentoById(req.getId());
		 return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
	 }

}
