package cz.uhk.planovac.jpa;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Skupina;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;

@Repository
@Transactional
public class EntityManagerPlanovac implements Planovac {

	@PersistenceContext
	private EntityManager em;

	// op. s uzivateli

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Uzivatel> vemUzivatele() {
		return this.em
				.createQuery(
						"SELECT uzivatel FROM Uzivatel uzivatel ORDER BY uzivatel.prijmeni, uzivatel.jmeno")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Uzivatel nactiUzivatele(int id) {
		return this.em.find(Uzivatel.class, id);
	}
	
	@Transactional
	public Uzivatel nactiUzivatelePodleLoginu(String login) {
		//return this.em.find(Uzivatel.class, login);
		Query query = this.em.createQuery("SELECT uzivatel FROM Uzivatel uzivatel WHERE uzivatel.login LIKE :login");
		query.setParameter("login", login );
		return (Uzivatel) query.getSingleResult();
	}
	
	public void ulozUzivatele(Uzivatel uzivatel) {
			Uzivatel merged = this.em.merge(uzivatel);
			this.em.flush();
			uzivatel.setIdUzivatele(merged.getIdUzivatele());
	}

	public void smazUzivatele(int id) throws DataAccessException {
		Uzivatel uzivatel = nactiUzivatele(id);
		this.em.remove(uzivatel);
	}

	// op. s udalostmi

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> vemUdalosti() {
		return this.em.createQuery(
				"SELECT udalost FROM Udalost udalost ORDER BY udalost.nazev")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Udalost nactiUdalost(int id) {
		return this.em.find(Udalost.class, id);
	}

	public void ulozUdalost(Udalost udalost) {
		/*Udalost merged = this.em.merge(udalost);
		this.em.flush();
		udalost.setIdUdalosti(merged.getIdUdalosti());*/
		this.em.persist(udalost);
	}

	public void smazUdalost(int id) throws DataAccessException {
		Udalost udalost = nactiUdalost(id);
		this.em.remove(udalost);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> nactiUdalostiDleUzivatele(int idUzivatele) {
		Query query = this.em.createQuery("SELECT udalost FROM Udalost udalost WHERE Udalost.vlastnikUz.idUzivatele LIKE :idUzivatele");
		query.setParameter("idUzivatele", idUzivatele );
		return query.getResultList();
	}

	// op. se skupinami

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Skupina> vemSkupiny() {
		return this.em.createQuery(
				"SELECT skupina FROM Skupina skupina ORDER BY skupina.nazev")
				.getResultList();

	}

	@Transactional(readOnly = true)
	public Skupina nactiSkupinu(int id) {
		return this.em.find(Skupina.class, id);
	}

	public void ulozSkupinu(Skupina skupina) {
		Skupina merged = this.em.merge(skupina);
		this.em.flush();
		skupina.setIdSkupiny(merged.getIdSkupiny());
	}

	public void smazSkupinu(int id) throws DataAccessException {
		Skupina skupina = nactiSkupinu(id);
		this.em.remove(skupina);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Uzivatel> nactiUzivateleDlePrijmeni(String prijmeni) {
		Query query = this.em.createQuery("SELECT uzivatel FROM Uzivatel uzivatel WHERE uzivatel.prijmeni LIKE :prijmeni");
		query.setParameter("prijmeni", prijmeni + "%");
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> nactiUdalostiDleZacatku(java.util.Date zacatek) throws DataAccessException {
		Query query = this.em.createQuery("SELECT udalost FROM Udalost udalost WHERE udalost.zacatek LIKE :zacatek");
		query.setParameter("zacatek", zacatek + "%");
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public ArrayList<ArrayList<Udalost>> nactiVsechnyUdalostiClenuSkupiny(Skupina skupina) throws DataAccessException {
		//Query query = this.em.createQuery("SELECT uzivatel FROM Uzivatel uzivatel WHERE uzivatel.seznamSkupin.idSkupiny LIKE :idSkupiny");
		//query.setParameter("idSkupiny", idSkupiny);
		//Collection<Uzivatel> cleni = query.getResultList()
		ArrayList<ArrayList<Udalost>> seznam = new ArrayList<ArrayList<Udalost>>();
		Collection<Uzivatel> cleni = skupina.getSeznamClenu();
		for (Uzivatel uzivatel : cleni) {
			uzivatel.setSeznamUdalosti(nactiUdalostiDleUzivatele(uzivatel.getIdUzivatele()));
			seznam.add(uzivatel.getSeznamUdalostiSerazene());
		}
		return seznam;
	}
	
	@Transactional(readOnly = true)
	public Collection<Uzivatel> nactiUzivateleDleSkupiny(int idSkupiny) throws DataAccessException {
		Skupina skupina = nactiSkupinu(idSkupiny);
		Collection<Uzivatel> cleni = skupina.getSeznamClenu();
		return cleni;
	}


}