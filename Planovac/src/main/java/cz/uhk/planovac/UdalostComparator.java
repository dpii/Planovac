package cz.uhk.planovac;

import java.util.Comparator;

class UdalostComparator implements Comparator<Object> {

	public int compare(Object emp1, Object emp2) {

		if (((Udalost) emp1).getZacatek().after(((Udalost) emp2).getZacatek()))
			return 1;
		else if (((Udalost) emp2).getZacatek().after(
				((Udalost) emp1).getZacatek()))
			return -1;
		else
			return 0;
	}

}