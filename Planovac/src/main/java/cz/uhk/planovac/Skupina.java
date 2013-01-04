package cz.uhk.planovac;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Skupina { // extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSkupiny;

	private String nazev;

	@ManyToMany(mappedBy = "seznamSkupin")
	private Set<Uzivatel> seznamClenu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUzivatele")
	private Uzivatel vedouci;

	@OneToMany(mappedBy = "vlastnikSk", fetch = FetchType.LAZY)
	private Set<Udalost> seznamUdalosti;

	public Integer getIdSkupiny() {
		return idSkupiny;
	}

	public void setIdSkupiny(Integer idSkupiny) {
		this.idSkupiny = idSkupiny;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public Set<Uzivatel> getSeznamClenu() {
		return seznamClenu;
	}

	public void setSeznamClenu(Set<Uzivatel> seznamClenu) {
		this.seznamClenu = seznamClenu;
	}

	public Uzivatel getVedouci() {
		return vedouci;
	}

	public void setVedouci(Uzivatel vedouci) {
		this.vedouci = vedouci;
	}

	public Set<Udalost> getSeznamUdalosti() {
		return seznamUdalosti;
	}

	public void setSeznamUdalosti(Set<Udalost> seznamUdalosti) {
		this.seznamUdalosti = seznamUdalosti;
	}

}
