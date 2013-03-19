package cz.uhk.planovac.validation;

import org.springframework.validation.Errors;

import cz.uhk.planovac.Vyhledavani;

public class VyhledavaniValidator {

	public void validate(Vyhledavani vyhledavani, Errors errors) {
		if (vyhledavani.getDelka()<0) {
			errors.rejectValue("delka", "number", "Mus� b�t kladn� cel� ��slo");
		}
		if (vyhledavani.getDoHodiny()<0) {
			errors.rejectValue("doHodiny", "number", "Mus� b�t kladn� cel� ��slo");
		}
		if (vyhledavani.getOdHodiny()<0) {
			errors.rejectValue("odHodiny", "number", "Mus� b�t kladn� cel� ��slo");
		}
		if (vyhledavani.getVyhledavaniPo()<0) {
			errors.rejectValue("vyhledavaniPo", "number", "Mus� b�t kladn� cel� ��slo");
		}
		
		if (vyhledavani.getOdData().after(vyhledavani.getDoData())) {
			errors.rejectValue("odData", "date", "Za��tek nesm� b�t po konci");
		}
		
		
	}
	
}
