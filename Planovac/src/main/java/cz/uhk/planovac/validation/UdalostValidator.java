package cz.uhk.planovac.validation;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import cz.uhk.planovac.Udalost;

/**
 * <code>Validator</code> for <code>Owner</code> forms.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class UdalostValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String DATE_PATTERN = 
            "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d";

	public void validate(Udalost udalost, Errors errors) {
		if (!StringUtils.hasLength(udalost.getNazev())) {
			errors.rejectValue("nazev", "required", "povinné pole");
		}
	
		
		
	}
	
	private boolean zkontrolujDatum(String datum) {
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(datum);
		return matcher.matches();
	}

}
