package cz.uhk.planovac;


import java.util.Collection;
import java.util.Date;

import org.springframework.dao.DataAccessException;

public interface Planovac {

	
	// operace pro praci s uzivateli
	
	Collection<Uzivatel> vemUzivatele() throws DataAccessException;

	void ulozUzivatele(Uzivatel uzivatel) throws DataAccessException;

	void smazUzivatele(int id) throws DataAccessException;
	
	Uzivatel nactiUzivatele(int id) throws DataAccessException;
	
	Collection<Uzivatel> nactiUzivateleDlePrijmeni(String prijmeni) throws DataAccessException;
	
	// operace pro praci s udalostmi

	Collection<Udalost> vemUdalosti() throws DataAccessException;

	void ulozUdalost(Udalost udalost) throws DataAccessException;

	void smazUdalost(int id) throws DataAccessException;
	
	Udalost nactiUdalost(int id) throws DataAccessException;
	
	Udalost nactiUdalostiDleZacatku(Date date) throws DataAccessException;
	
	// operace pro praci se skupinami
	
	Collection<Skupina> vemSkupiny() throws DataAccessException;

	void ulozSkupinu(Skupina Skupina) throws DataAccessException;

	void smazSkupinu(int id) throws DataAccessException;
	
	Skupina nactiSkupinu(int id) throws DataAccessException;
	

}
