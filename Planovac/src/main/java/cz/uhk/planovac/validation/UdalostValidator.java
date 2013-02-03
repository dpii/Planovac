package cz.uhk.planovac.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import cz.uhk.planovac.Udalost;

public class UdalostValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String DATE_PATTERN = 
            "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d";

	public void validate(Udalost udalost, Errors errors) {
		if (!StringUtils.hasLength(udalost.getNazev())) {
			errors.rejectValue("nazev", "required", "Povinné pole");
		}
		
		if (udalost.getZacatek().after(udalost.getKonec())) {
			errors.rejectValue("zacatek", "date", "Zaèátek události nesmí být po konci");
		}
		
		
	}
	
	private boolean zkontrolujDatum(String datum) {
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(datum);
		return matcher.matches();
	}

}
