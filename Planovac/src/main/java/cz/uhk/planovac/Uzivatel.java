package cz.uhk.planovac;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="UZIVATELE")
public class Uzivatel {// extends BaseEntity {

	private Integer idUzivatele;

	private String login;

	private Set<Udalost> seznamUdalosti;
	
	private Set<Skupina> seznamSkupin;
	
	private Set<Skupina> vedeneSkupiny;

	private String jmeno;

	private String prijmeni;

	private String email;

	private String heslo_hash;
	
	private boolean povolen;

	private String role;

	private Date datumZalozeni;

	private Date datumPosledniPrihlaseni;

	private String adresa;

	private String mesto;

	private String telefon;
	
	@Transient
	public boolean isNew() {
		return (this.idUzivatele == null);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdUzivatele() {
		return idUzivatele;
	}

	public void setIdUzivatele(Integer idUzivatele) {
		this.idUzivatele = idUzivatele;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@OneToMany(mappedBy = "vlastnikUz", fetch = FetchType.LAZY)
	public Set<Udalost> getSeznamUdalosti() {
		return seznamUdalosti;
	}

	public void setSeznamUdalosti(Set<Udalost> seznamUdalosti) {
		this.seznamUdalosti = seznamUdalosti;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeslo_hash() {
		return heslo_hash;
	}

	public void setHeslo_hash(String heslo_hash) {
		this.heslo_hash = heslo_hash;
	}
	
	public boolean isPovolen() {
		return povolen;
	}

	public void setPovolen(boolean povolen) {
		this.povolen = povolen;
	}

	public Date getDatumZalozeni() {
		return datumZalozeni;
	}

	public void setDatumZalozeni(Date datumZalozeni) {
		this.datumZalozeni = datumZalozeni;
	}

	public Date getDatumPosledniPrihlaseni() {
		return datumPosledniPrihlaseni;
	}

	public void setDatumPosledniPrihlaseni(Date datumPosledniPrihlaseni) {
		this.datumPosledniPrihlaseni = datumPosledniPrihlaseni;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}
	
	@ManyToMany
	@JoinTable(name="SKUPINY_UZIVATELU",
	   joinColumns=@JoinColumn(name="idUzivatele"),
	   inverseJoinColumns=@JoinColumn(name="idSkupiny"))
	public Set<Skupina> getSeznamSkupin() {
		return seznamSkupin;
	}

	public void setSeznamSkupin(Set<Skupina> seznamSkupin) {
		this.seznamSkupin = seznamSkupin;
	}
	
	@OneToMany(mappedBy = "vedouci", fetch = FetchType.LAZY)
	@JoinTable(name="VEDOUCI_SKUPIN",
	   joinColumns=@JoinColumn(name="idUzivatele"),
	   inverseJoinColumns=@JoinColumn(name="idSkupiny"))
	public Set<Skupina> getVedeneSkupiny() {
		return vedeneSkupiny;
	}

	public void setVedeneSkupiny(Set<Skupina> vedeneSkupiny) {
		this.vedeneSkupiny = vedeneSkupiny;
	}

}
