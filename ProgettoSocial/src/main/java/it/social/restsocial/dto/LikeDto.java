package it.social.restsocial.dto;

import java.time.Instant;
import java.time.LocalDateTime;



public class LikeDto {

	private Long id;
	private UtenteDto utente;
	private PostDto post;
	private LocalDateTime dataOra;
	private Instant createdAt;
	private Instant updatedAt;
	
	public LikeDto() {}
	
	public LikeDto(Long id, UtenteDto utente, PostDto post, LocalDateTime dataOra, Instant createdAt,
			Instant updatedAt) {
		super();
		this.id = id;
		this.utente = utente;
		this.post = post;
		this.dataOra = dataOra;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UtenteDto getUtente() {
		return utente;
	}
	public void setUtente(UtenteDto utente) {
		this.utente = utente;
	}
	public PostDto getPost() {
		return post;
	}
	public void setPost(PostDto post) {
		this.post = post;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public Instant getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
