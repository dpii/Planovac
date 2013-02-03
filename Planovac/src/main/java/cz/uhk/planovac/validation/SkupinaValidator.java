package cz.uhk.planovac.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import cz.uhk.planovac.Skupina;

public class SkupinaValidator {
	public void validate(Skupina skupina, Errors errors) {
		if (!StringUtils.hasLength(skupina.getNazev())) {
			errors.rejectValue("nazev", "required", "Povinné pole");
		}
		
		
	}
}
