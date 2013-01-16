package cz.uhk.planovac;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Uzivatel {// extends BaseEntity {

	private Integer idUzivatele;

	private String login;

	@OneToMany(mappedBy = "vlastnikUz", fetch = FetchType.LAZY)
	private Set<Udalost> seznamUdalosti;

	@ManyToMany
	private Set<Skupina> seznamSkupin;

	@OneToMany(mappedBy = "vedouci", fetch = FetchType.LAZY)
	private Set<Skupina> vedeneSkupiny;

	private String jmeno;

	private String prijmeni;

	private String email;

	private String heslo_hash;

	private Date datumZalozeni;

	private Date datumPosledniPrihlaseni;

	private String adresa;

	private String mesto;

	private String telefon;
	
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

	public Set<Udalost> getSeznamUdalosti() {
		return seznamUdalosti;
	}

	public void setSeznamUdalosti(Set<Udalost> seznamUdalosti) {
		this.seznamUdalosti = seznamUdalosti;
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

	public Set<Skupina> getSeznamSkupin() {
		return seznamSkupin;
	}

	public void setSeznamSkupin(Set<Skupina> seznamSkupin) {
		this.seznamSkupin = seznamSkupin;
	}

	public Set<Skupina> getVedeneSkupiny() {
		return vedeneSkupiny;
	}

	public void setVedeneSkupiny(Set<Skupina> vedeneSkupiny) {
		this.vedeneSkupiny = vedeneSkupiny;
	}

}
