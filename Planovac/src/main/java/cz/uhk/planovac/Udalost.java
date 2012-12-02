package cz.uhk.planovac;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name="udalosti")
public class Udalost{// extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUdalosti;
	
	private String nazev;
	
	private boolean verejna;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idSkupiny")
	private Skupina vlastnikSk;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUzivatele")
	private Uzivatel vlastnikUz;
	
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

	public Skupina getVlastnikSk() {
		return vlastnikSk;
	}

	public void setVlastnikSk(Skupina vlastnikSk) {
		this.vlastnikSk = vlastnikSk;
	}

	public Uzivatel getVlastnikUz() {
		return vlastnikUz;
	}

	public void setVlastnikUz(Uzivatel vlastnikUz) {
		this.vlastnikUz = vlastnikUz;
	}
	
	

	
	
	
	
	
}
	
