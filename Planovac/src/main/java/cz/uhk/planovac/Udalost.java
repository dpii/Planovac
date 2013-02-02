package cz.uhk.planovac;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;

@Entity
@Table(name = "UDALOSTI")
public class Udalost {// extends BaseEntity {

	private Integer idUdalosti;

	private String nazev;
	
	private String popis;

	private boolean verejna;

	private Skupina vlastnikSk;

	private Uzivatel vlastnikUz;
	
	private Collection<Uzivatel> ucastnici;

	// blob? nebo odkaz
	private String obrazek;

	private Date zacatek;

	private Date konec;

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getObrazek() {
		return obrazek;
	}

	public void setObrazek(String obrazek) {
		this.obrazek = obrazek;
	}

	public Date getZacatek() {
		return zacatek;
	}

	public void setZacatek(Date zacatek) {
		this.zacatek = zacatek;
	}

	public Date getKonec() {
		return konec;
	}

	public void setKonec(Date konec) {
		this.konec = konec;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdUdalosti() {
		return idUdalosti;
	}

	public void setIdUdalosti(Integer idUdalosti) {
		this.idUdalosti = idUdalosti;
	}

	public boolean isVerejna() {
		return verejna;
	}

	public void setVerejna(boolean verejna) {
		this.verejna = verejna;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idVlastnikaSk")
	public Skupina getVlastnikSk() {
		Hibernate.initialize(vlastnikSk);
		return vlastnikSk;
	}

	public void setVlastnikSk(Skupina vlastnikSk) {
		this.vlastnikSk = vlastnikSk;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idVlastnikaUz")
	public Uzivatel getVlastnikUz() {
		Hibernate.initialize(vlastnikUz);
		return vlastnikUz;
	}

	public void setVlastnikUz(Uzivatel vlastnikUz) {
		this.vlastnikUz = vlastnikUz;
	}

	// konstruktor pro ManazerUdalosti - pro dočasné ukládání časových úseků
	public Udalost(Date zacatek, Date konec) {
		super();
		this.zacatek = zacatek;
		this.konec = konec;
	}

	// prazdny konstruktor pro form
	public Udalost() {
		super();
	}
	
	
	@ManyToMany(mappedBy = "seznamUdalosti")
	public Collection<Uzivatel> getUcastnici() {
		Hibernate.initialize(ucastnici);
		return ucastnici;
	}

	public void setUcastnici(Collection<Uzivatel> ucastnici) {
		this.ucastnici = ucastnici;
	}
	
	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	@Transient
	public boolean isNew() {
		return (this.idUdalosti == null);
	}

}
