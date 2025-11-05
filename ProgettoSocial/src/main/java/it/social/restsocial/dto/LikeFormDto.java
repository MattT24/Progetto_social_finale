package it.social.restsocial.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public class LikeFormDto {

	private Long id;
	
    @NotNull(message = "L'utente è obbligatorio")
	private UtenteDto utente;
	
    @NotNull(message = "Il post è obbligatorio")
	private PostDto post;
	
    @NotNull(message = "Il contenuto non può essere vuoto")
	private LocalDateTime dataOra;
	
	
	public LikeFormDto(){}
	
	public LikeFormDto(Long id, UtenteDto utente, PostDto post, LocalDateTime dataOra) {
		this.id = id;
		this.utente = utente;
		this.post = post;
		this.dataOra=dataOra;
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
	
	
}
