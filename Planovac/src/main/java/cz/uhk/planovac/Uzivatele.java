package cz.uhk.planovac;

import java.util.ArrayList;
import java.util.List;


public class Uzivatele {
	
	private List<Uzivatel> uzivatele;

	public List<Uzivatel> nactiUzivateleList() {
		if (uzivatele == null) {
			uzivatele = new ArrayList<Uzivatel>();
		}
		return uzivatele;
	}

}
