package cz.uhk.planovac;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

public interface Planovac {

	
	Collection<Uzivatel> vemUzivatele() throws DataAccessException;

	void ulozUvivatele(Uzivatel uzivatel) throws DataAccessException;

	void smazUzivatele(int id) throws DataAccessException;

	Collection<Udalost> vemUdalosti() throws DataAccessException;

	void ulozUvivatele(Udalost udalost) throws DataAccessException;

	void smazUdalost(int id) throws DataAccessException;

}
