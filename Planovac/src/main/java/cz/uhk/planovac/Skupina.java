package cz.uhk.planovac;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;

@Entity
@Table(name="SKUPINY")
public class Skupina {

	
	private Integer idSkupiny;

	private String nazev;

	private Collection<Uzivatel> seznamClenu;

	private Uzivatel vedouci;
	
	private boolean verejna;
	
	private String popis;

	private Collection<Udalost> seznamUdalosti;

	
	
	public boolean isVerejna() {
		return verejna;
	}

	public void setVerejna(boolean verejna) {
		this.verejna = verejna;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	//@Transactional(readOnly = true) - nefunguje ani s tím
	
	//EAGER zmìnit na LAZY až se najde øešení "could not initialize" chyby!
	@ManyToMany(mappedBy = "seznamSkupin", fetch = FetchType.LAZY)
	public Collection<Uzivatel> getSeznamClenu() {
		Hibernate.initialize(seznamClenu);
		return seznamClenu;
	}

	public void setSeznamClenu(Collection<Uzivatel> seznamClenu) {
		this.seznamClenu = seznamClenu;
	}
	
	//EAGER zmìnit na LAZY až se najde øešení "could not initialize" chyby!
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idVedouciho")
	public Uzivatel getVedouci() {
		//Hibernate.initialize(vedouci);
		return vedouci;
	}

	public void setVedouci(Uzivatel vedouci) {
		this.vedouci = vedouci;
	}

	@OneToMany(mappedBy = "vlastnikSk", fetch = FetchType.LAZY)
	public Collection<Udalost> getSeznamUdalosti() {
		Hibernate.initialize(seznamUdalosti);
		return seznamUdalosti;
	}

	public void setSeznamUdalosti(Collection<Udalost> seznamUdalosti) {
		this.seznamUdalosti = seznamUdalosti;
	}
	
	public Skupina()
	{
		super();
	}
	
	@Transient
	@Override
	public String toString()
	{
		return getNazev();
	}
	
	@Transient
	public boolean isNew() {
		return (this.idSkupiny == null);
	}

}
