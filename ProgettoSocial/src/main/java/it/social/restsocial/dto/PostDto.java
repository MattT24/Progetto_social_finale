package it.social.restsocial.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

	private Long id;
	private UtenteDto utente;
	private LocalDateTime dataOra;
	private String contenuto;
    private Instant createdAt;
    private Instant updatedAt;
    private List<CommentoDto> commenti;
    private List<LikeDto> like;
    
    public PostDto(){}
    
	public PostDto(Long id, UtenteDto utente, LocalDateTime dataOra, String contenuto, Instant createdAt,
			Instant updatedAt, List<CommentoDto> commenti, List<LikeDto> like) {
		this.id = id;
		this.utente = utente;
		this.dataOra = dataOra;
		this.contenuto = contenuto;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.commenti = commenti;
		this.like = like;
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
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
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
	public List<CommentoDto> getCommenti() {
		return commenti;
	}
	public void setCommenti(List<CommentoDto> commenti) {
		this.commenti = commenti;
	}
	public List<LikeDto> getLike() {
		return like;
	}
	public void setLike(List<LikeDto> like) {
		this.like = like;
	}
	
    
}
