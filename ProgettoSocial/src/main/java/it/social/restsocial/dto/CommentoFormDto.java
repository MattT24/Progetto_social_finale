package it.social.restsocial.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentoFormDto {

	private Long id;

    @NotNull(message = "Il post è obbligatorio")
	private PostDto post;

    @NotNull(message = "L'utente è obbligatorio")
	private UtenteDto utente;
	
    @NotNull(message = "Il contenuto non può essere vuoto")
	private LocalDateTime dataOra;
	
    @NotBlank(message = "Il testo non può essere vuoto")
	private String testo;
	
	public CommentoFormDto() {}
	
	public CommentoFormDto(Long id, PostDto post, UtenteDto utente, LocalDateTime dataOra, String testo) {
		this.id = id;
		this.post = post;
		this.utente = utente;
		this.dataOra = dataOra;
		this.testo = testo;
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
	
	
}
