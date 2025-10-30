package it.social.restsocial.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostFormDto {

	private Long id;
	
    @NotNull(message = "L'utente è obbligatorio")
	private UtenteDto utente;

    @NotNull(message = "La data non può essere vuota")
	private LocalDateTime dataOra;

    @NotBlank(message = "Il contenuto non può essere vuoto")
	private String contenuto;

	public PostFormDto() {}
	
	public PostFormDto(Long id, UtenteDto utente, LocalDateTime dataOra, String contenuto) {
		super();
		this.id = id;
		this.utente = utente;
		this.dataOra = dataOra;
		this.contenuto = contenuto;
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
	
	
}
