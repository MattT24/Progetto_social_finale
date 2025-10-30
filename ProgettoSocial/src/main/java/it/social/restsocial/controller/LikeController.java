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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.social.restsocial.dto.IdRequest;
import it.social.restsocial.dto.LikeDto;
import it.social.restsocial.dto.LikeFormDto;
import it.social.restsocial.dto.PageResponse;
import it.social.restsocial.dto.PostDto;
import it.social.restsocial.service.LikeService;
import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/like")
public class LikeController {

	@Autowired private LikeService service;

	@PostMapping
	@PreAuthorize("hasAuthority('LIKE_CREATE')")
	public ResponseEntity<LikeDto> create (@Valid @RequestBody LikeFormDto dto) {
		var create = service.create(dto);
		 return ResponseEntity.status(201).body(create);
	}
	
	 @DeleteMapping
	 @PreAuthorize("hasAuthority('LIKE_DELETE')")
	 public ResponseEntity<Void> deleteMyLike(@RequestBody IdRequest req){
		 service.deleteMyLike(req.getId());
		 return ResponseEntity.noContent().build();
	 }
	 
	 @GetMapping("/post")
	 @PreAuthorize("hasAuthority('LIKE_READ')")
	 public PageResponse<LikeDto> allLikeByPost(@RequestParam Long postId, Pageable pageable){  //List o PageResponse o Responseentity?
		 return service.allLikeByPost(postId, pageable);
	 }
	 
	 @GetMapping("/my")
	 @PreAuthorize("hasAuthority('LIKE_READ')")
	 public PageResponse<LikeDto> getMyLikedPost(Pageable pageable){
		 return service.allPostByLike(pageable);
	 }
}
