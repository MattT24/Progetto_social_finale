package it.social.restsocial.dto;

import java.time.Instant;
import java.time.LocalDateTime;


public class CommentoDto {

	private Long id;
	private PostDto post;
	private UtenteDto utente;
	private LocalDateTime dataOra;
	private String testo;
	private Instant createdAt;
	private Instant updatedAt;
	
	public CommentoDto() {}
	
	public CommentoDto(Long id, PostDto post, UtenteDto utente, LocalDateTime dataOra, String testo, Instant createdAt,
			Instant updatedAt) {
		super();
		this.id = id;
		this.post = post;
		this.utente = utente;
		this.dataOra = dataOra;
		this.testo = testo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PostDto getPost() {
		return post;
	}
	public void setPost(PostDto post) {
		this.post = post;
	}
	public UtenteDto getUtente() {
		return utente;
	}
	public void setUtente(UtenteDto utente) {
		this.utente = utente;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
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
