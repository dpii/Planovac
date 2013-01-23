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
@Table(name="UDALOSTI")
public class Udalost{// extends BaseEntity {

	
	private Integer idUdalosti;
	
	private String nazev;
	
	private boolean verejna;
	
	
	private Skupina vlastnikSk;
	

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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idSkupiny")
	public Skupina getVlastnikSk() {
		return vlastnikSk;
	}

	public void setVlastnikSk(Skupina vlastnikSk) {
		this.vlastnikSk = vlastnikSk;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUzivatele")
	public Uzivatel getVlastnikUz() {
		return vlastnikUz;
	}

	public void setVlastnikUz(Uzivatel vlastnikUz) {
		this.vlastnikUz = vlastnikUz;
	}
	
	

	
	
	
	
	
}
	
