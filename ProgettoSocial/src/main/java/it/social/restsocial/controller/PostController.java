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
import org.springframework.web.bind.annotation.RestController;

import it.social.restsocial.dto.IdRequest;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.PostDto;
import it.social.restsocial.dto.PostFormDto;

import it.social.restsocial.service.PostService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired private PostService service;

	 @PostMapping
	 @PreAuthorize("hasAuthority('POST_CREATE')")
	 public ResponseEntity<PostDto> create (@Valid @RequestBody PostFormDto form) {
		 var dto = service.create(form);
		 return ResponseEntity.status(201).body(dto);
	 }
	 
	 @PutMapping
	 @PreAuthorize("hasAuthority('POST_UPDATE')")
	 public ResponseEntity<PostDto> updateMyPost (@Valid @RequestBody PostFormDto dto) {
		 var updated = service.updateMyPost(dto);
		 return ResponseEntity.status(201).body(updated);
	 }
	 
	 @DeleteMapping
	 @PreAuthorize("hasAuthority('POST_DELETE')")
	 public ResponseEntity<Void> delete(@RequestBody IdRequest req){
		 service.delete(req.getId());
		 return ResponseEntity.noContent().build();
	 }
	 @DeleteMapping("/mio")
	 @PreAuthorize("hasAuthority('POST_MY_DELETE')")
	 public ResponseEntity<Void> deleteMyPost(@RequestBody IdRequest req){
		 service.deleteMyPost(req.getId());
		 return ResponseEntity.noContent().build();
	 }
	 
	 @GetMapping
	 @PreAuthorize("hasAuthority('POST_READ')")
	 public PageResponse<PostDto> listAll(Pageable pageable){  //List o PageResponse o ResponseEntity?
		 return service.listAll(pageable);
	 }
	 
	 @GetMapping("/post_utente")
	 @PreAuthorize("hasAuthority('POST_READ')")
	 public PageResponse<PostDto> allPostByUtente(@Valid @RequestBody IdRequest req, Pageable pageable){ //Utente o id?
		 return service.allPostByUtente(req.getId(), pageable); 
	 }
	 @GetMapping("/miei")
	 @PreAuthorize("hasAuthority('POST_READ')")
	 public PageResponse<PostDto> allMyPost(Pageable pageable){
		 return service.allMyPost(pageable); 
	 }
	 
	 @GetMapping("/byId")
	 @PreAuthorize("hasAuthority('POST_READ')")
	 public ResponseEntity<PostDto> postById(@RequestBody IdRequest req) {
		 var dto = service.postById(req.getId());
		 return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
	 }
	 
}