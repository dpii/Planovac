package cz.uhk.planovac.validation;

import org.springframework.validation.Errors;

import cz.uhk.planovac.Vyhledavani;

public class VyhledavaniValidator {

	public void validate(Vyhledavani vyhledavani, Errors errors) {
		if (vyhledavani.getDelka()<0) {
			errors.rejectValue("delka", "number", "Musí být kladné celé èíslo");
		}
		if (vyhledavani.getDoHodiny()<0) {
			errors.rejectValue("doHodiny", "number", "Musí být kladné celé èíslo");
		}
		if (vyhledavani.getOdHodiny()<0) {
			errors.rejectValue("odHodiny", "number", "Musí být kladné celé èíslo");
		}
		if (vyhledavani.getVyhledavaniPo()<0) {
			errors.rejectValue("vyhledavaniPo", "number", "Musí být kladné celé èíslo");
		}
		
		if (vyhledavani.getOdData().after(vyhledavani.getDoData())) {
			errors.rejectValue("odData", "date", "Zaèátek nesmí být po konci");
		}
		
		
	}
	
}
