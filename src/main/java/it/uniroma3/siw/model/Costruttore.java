package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Costruttore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private Integer annoFondazione;
	@NotBlank
	private String nazione;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "TEXT")
	private String immagine;
	
	@OneToMany(mappedBy="costruttore")
	private List<Auto> macchine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAnnoFondazione() {
		return annoFondazione;
	}

	public void setAnnoFondazione(Integer annoFondazione) {
		this.annoFondazione = annoFondazione;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public List<Auto> getMacchine() {
		return macchine;
	}

	public void setMacchine(List<Auto> macchine) {
		this.macchine = macchine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annoFondazione, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Costruttore other = (Costruttore) obj;
		return Objects.equals(annoFondazione, other.annoFondazione) && Objects.equals(nome, other.nome);
	}


	

}
