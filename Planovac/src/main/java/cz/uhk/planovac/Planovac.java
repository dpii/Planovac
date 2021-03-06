package cz.uhk.planovac;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.dao.DataAccessException;

public interface Planovac {

	
	// operace pro praci s uzivateli
	
	Collection<Uzivatel> vemUzivatele() throws DataAccessException;

	void ulozUzivatele(Uzivatel uzivatel) throws DataAccessException;

	void smazUzivatele(int id) throws DataAccessException;
	
	Uzivatel nactiUzivatele(int id) throws DataAccessException;
	
	Uzivatel nactiUzivatelePodleLoginu(String login) throws DataAccessException;
	
	Collection<Uzivatel> nactiUzivateleDlePrijmeni(String prijmeni) throws DataAccessException;
	
	boolean jeLoginVolny(String login) throws DataAccessException;
	
	Collection<Uzivatel> nactiUzivateleDleUdalosti(int idUdalosti) throws DataAccessException;

	Collection<Uzivatel> nactiUzivateleDleSkupiny(int idSkupiny) throws DataAccessException;
	
	// operace pro praci s udalostmi

	Collection<Udalost> vemUdalosti() throws DataAccessException;

	void ulozUdalost(Udalost udalost) throws DataAccessException;

	void smazUdalost(int id) throws DataAccessException;
	
	Udalost nactiUdalost(int id) throws DataAccessException;
	
	Collection<Udalost> nactiUdalostiDleZacatku(Date date) throws DataAccessException;
	
	Collection<Udalost> nactiUdalostiDleUzivatele(int idUzivatele) throws DataAccessException;
	
	Collection<Udalost> nactiUdalostiDleSkupiny(int idSkupiny) throws DataAccessException;
	
	ArrayList<ArrayList<Udalost>> nactiVsechnyUdalostiClenuSkupiny(Skupina skupina) throws DataAccessException;
	
	Uzivatel nactiVlastnikaUdalosti(int idUdalosti) throws DataAccessException;
	
	Collection<Udalost> vemVerejneUdalosti() throws DataAccessException;
	
	void pridatUzivateleKUdalosti(int idUzivatele, int idSkupiny) throws DataAccessException;
	
	void odebratUzivateleZUdalosti(int idUzivatele, int idSkupiny) throws DataAccessException;
	
	// operace pro praci se skupinami
	
	Collection<Skupina> vemSkupiny() throws DataAccessException;
	
	Collection<Skupina> vemVerejneSkupiny() throws DataAccessException;
	
	Skupina nactiSkupinu(int id) throws DataAccessException;

	void ulozSkupinu(Skupina Skupina) throws DataAccessException;

	void smazSkupinu(int id) throws DataAccessException;
	
	void pridatUzivateleDoSkupiny(int idUzivatele, int idSkupiny) throws DataAccessException;
	
	void odebratUzivateleZeSkupiny(int idUzivatele, int idSkupiny) throws DataAccessException;
	

}
