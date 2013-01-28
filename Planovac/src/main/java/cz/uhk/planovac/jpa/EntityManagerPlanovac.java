package cz.uhk.planovac.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
						"SELECT uzivatel FROM Uzivatel uzivatel ORDER BY uzivatel.prijmeni, vet.jmeno")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Uzivatel nactiUzivatele(int id) {
		return this.em.find(Uzivatel.class, id);
	}
	
	public Uzivatel nactiUzivatelePodleLoginu(String login) {
		return this.em.find(Uzivatel.class, login);
	}
	
	@Transactional//p�id�no nav�c
	public void ulozUzivatele(Uzivatel uzivatel) {
		/*if(uzivatel.isNew())//nevyřešilo to problém s nullPointerE., ale někde jsem četl, že se to možná má použít pro nové položky
		{
			this.em.persist(uzivatel);
		}
		else
		{*/
			Uzivatel merged = this.em.merge(uzivatel);
			this.em.flush();
			uzivatel.setIdUzivatele(merged.getIdUzivatele());
		//}
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
		Udalost merged = this.em.merge(udalost);
		this.em.flush();
		udalost.setIdUdalosti(merged.getIdUdalosti());
	}

	public void smazUdalost(int id) throws DataAccessException {
		Udalost udalost = nactiUdalost(id);
		this.em.remove(udalost);
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

}