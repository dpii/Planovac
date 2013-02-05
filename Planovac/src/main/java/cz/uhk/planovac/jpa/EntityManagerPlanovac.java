package cz.uhk.planovac.jpa;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

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

	//@PersistenceContext
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
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
	
	public void ulozUzivatele(Uzivatel uzivatel) {
			Uzivatel merged = this.em.merge(uzivatel);
			this.em.flush();
			uzivatel.setIdUzivatele(merged.getIdUzivatele());
	}

	public void smazUzivatele(int id) throws DataAccessException {
		Uzivatel uzivatel = nactiUzivatele(id);
		this.em.remove(uzivatel);
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
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Uzivatel> nactiUzivateleDlePrijmeni(String prijmeni) {
		Query query = this.em.createQuery("SELECT uzivatel FROM Uzivatel uzivatel WHERE uzivatel.prijmeni LIKE :prijmeni");
		query.setParameter("prijmeni", prijmeni + "%");
		return query.getResultList();
	}
	
	@Transactional
	public boolean jeLoginVolny(String login) {
		//return this.em.find(Uzivatel.class, login);
		Query query = this.em.createQuery("SELECT uzivatel FROM Uzivatel uzivatel WHERE uzivatel.login LIKE :login");
		query.setParameter("login", login );
		if(query.getResultList().size()==0)
			return true;
		return false;
	}
	
	@Transactional(readOnly = true)
	public Collection<Uzivatel> nactiUzivateleDleSkupiny(int idSkupiny) throws DataAccessException {
		Skupina skupina = nactiSkupinu(idSkupiny);
		Collection<Uzivatel> cleni = skupina.getSeznamClenu();
		return cleni;
	}
	
	@Transactional(readOnly = true)
	public Collection<Uzivatel> nactiUzivateleDleUdalosti(int idUdalosti) throws DataAccessException {
		Udalost udalost = nactiUdalost(idUdalosti);
		Collection<Uzivatel> cleni = udalost.getUcastnici();
		return cleni;
	}

	// op. s udalostmi

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> vemUdalosti() {
		return this.em.createQuery(
				"SELECT udalost FROM Udalost udalost ORDER BY udalost.nazev")
				.getResultList();
	}

	public void ulozUdalost(Udalost udalost) {
		Udalost merged = this.em.merge(udalost);
		this.em.flush();
		udalost.setIdUdalosti(merged.getIdUdalosti());
		//this.em.persist(udalost);
	}

	public void smazUdalost(int id) throws DataAccessException {
		Query query = this.em.createNativeQuery("DELETE FROM udalosti_uzivatelu WHERE idUdalosti LIKE :id");
		query.setParameter("id", id );
		query.executeUpdate();
		query = this.em.createNativeQuery("DELETE FROM udalosti WHERE idUdalosti LIKE :id");
		query.setParameter("id", id );
		query.executeUpdate();
	}

	@Transactional(readOnly = true)
	public Udalost nactiUdalost(int id) {
		return this.em.find(Udalost.class, id);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> nactiUdalostiDleZacatku(java.util.Date zacatek) throws DataAccessException {
		Query query = this.em.createQuery("SELECT udalost FROM Udalost udalost WHERE udalost.zacatek LIKE :zacatek");
		query.setParameter("zacatek", zacatek + "%");
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> nactiUdalostiDleUzivatele(int idUzivatele) {
		Query query = this.em.createQuery("SELECT udalost FROM Udalost udalost WHERE Udalost.vlastnikUz.idUzivatele LIKE :idUzivatele");
		query.setParameter("idUzivatele", idUzivatele );
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> nactiUdalostiDleSkupiny(int idSkupiny) throws DataAccessException {
		Query query = this.em.createQuery("SELECT udalost FROM Udalost udalost JOIN udalost.vlastnikSk skupina WHERE skupina.idSkupiny LIKE :idSkupiny");
		query.setParameter("idSkupiny", idSkupiny );
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public ArrayList<ArrayList<Udalost>> nactiVsechnyUdalostiClenuSkupiny(Skupina skupina) throws DataAccessException {
		ArrayList<ArrayList<Udalost>> seznam = new ArrayList<ArrayList<Udalost>>();
		
		//Collection<Uzivatel> cleni = skupina.getSeznamClenu();//dataAccessFailure
		Collection<Uzivatel> cleni = nactiUzivateleDleSkupiny(skupina.getIdSkupiny());
		for (Uzivatel uzivatel : cleni) {
			uzivatel.setSeznamUdalosti(nactiUdalostiDleUzivatele(uzivatel.getIdUzivatele()));
			seznam.add(uzivatel.getSeznamUdalostiSerazene());
		}
		return seznam;
	}
	
	public Uzivatel nactiVlastnikaUdalosti(int idUdalosti) throws DataAccessException
	{
		Query query = this.em.createQuery("SELECT uzivatel FROM Udalost udalost JOIN udalost.vlastnikUz uzivatel WHERE udalost.idUdalosti LIKE :idUdalosti");
		query.setParameter("idUdalosti", idUdalosti );
		return (Uzivatel)query.getSingleResult();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Udalost> vemVerejneUdalosti() {
		return this.em.createQuery(
				"SELECT udalost FROM Udalost udalost WHERE udalost.verejna LIKE 1 ORDER BY udalost.nazev")
				.getResultList();
	}
	
	public void pridatUzivateleKUdalosti(int idUzivatele, int idUdalosti) throws DataAccessException {
		Query query = this.em.createNativeQuery("INSERT IGNORE INTO udalosti_uzivatelu (`idUdalosti`, `idUzivatele`) VALUES (:idUdalosti, :idUzivatele)");
		query.setParameter("idUzivatele", idUzivatele );
		query.setParameter("idUdalosti", idUdalosti );
		query.executeUpdate();
	}
	public void odebratUzivateleZUdalosti(int idUzivatele, int idUdalosti) throws DataAccessException {
		Query query = this.em.createNativeQuery("DELETE FROM udalosti_uzivatelu WHERE idUdalosti LIKE :idUdalosti AND idUzivatele LIKE :idUzivatele");
		query.setParameter("idUzivatele", idUzivatele );
		query.setParameter("idUdalosti", idUdalosti );
		query.executeUpdate();
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
	@SuppressWarnings("unchecked")
	public Collection<Skupina> vemVerejneSkupiny() {
		return this.em.createQuery(
				"SELECT skupina FROM Skupina skupina WHERE skupina.verejna LIKE 1 ORDER BY skupina.nazev")
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
		Collection<Udalost> udalosti = nactiUdalostiDleSkupiny(id);
		for (Udalost udalost : udalosti) {
			smazUdalost(udalost.getIdUdalosti());
		}
		Query query = this.em.createNativeQuery("DELETE FROM skupiny_uzivatelu WHERE idSkupiny LIKE :id");
		query.setParameter("id", id );
		query.executeUpdate();
		query = this.em.createNativeQuery("DELETE FROM skupiny WHERE idSkupiny LIKE :id");
		query.setParameter("id", id );
		query.executeUpdate();
	}
	
	public void pridatUzivateleDoSkupiny(int idUzivatele, int idSkupiny) throws DataAccessException {
		Query query = this.em.createNativeQuery("INSERT IGNORE INTO skupiny_uzivatelu (`idSkupiny`, `idUzivatele`) VALUES (:idSkupiny, :idUzivatele)");
		query.setParameter("idUzivatele", idUzivatele );
		query.setParameter("idSkupiny", idSkupiny );
		query.executeUpdate();
	}
	public void odebratUzivateleZeSkupiny(int idUzivatele, int idSkupiny) throws DataAccessException {
		Query query = this.em.createNativeQuery("DELETE FROM skupiny_uzivatelu WHERE idSkupiny LIKE :idSkupiny AND idUzivatele LIKE :idUzivatele");
		query.setParameter("idUzivatele", idUzivatele );
		query.setParameter("idSkupiny", idSkupiny );
		query.executeUpdate();
	}

}