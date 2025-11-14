package it.social.restsocial.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "domande_utenti")
@EntityListeners(AuditingEntityListener.class)
public class DomandaUtente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "domanda_id")
    private DomandaSicurezza domanda;
	
	@ManyToOne
    @JoinColumn(name = "utente_id", unique = true)
    private Utente utente;
	
	@Column(name="risposta" ,nullable = false)
    private String risposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DomandaSicurezza getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaSicurezza domanda) {
		this.domanda = domanda;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	
	

}
