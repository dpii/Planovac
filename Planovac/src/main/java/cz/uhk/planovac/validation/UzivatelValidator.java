package cz.uhk.planovac.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import cz.uhk.planovac.Uzivatel;

/**
 * <code>Validator</code> for <code>Owner</code> forms.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class UzivatelValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public void validate(Uzivatel uzivatel, Errors errors) {
		if (!StringUtils.hasLength(uzivatel.getLogin())) {
			errors.rejectValue("login", "required", "Povinné pole");
		}
		if (!StringUtils.hasLength(uzivatel.getHeslo_hash())) {
			errors.rejectValue("heslo_hash", "required", "Povinné pole");
		}
		if (!StringUtils.hasLength(uzivatel.getEmail())) {
			errors.rejectValue("email", "required", "Povinné pole");
		}
		if (!jeMailOk(uzivatel.getEmail())) {
			errors.rejectValue("email", "email" ,"Špatný formát");
		}

		String telefon = uzivatel.getTelefon();
		if(StringUtils.hasLength(telefon))
		{
			for (int i = 0; i < telefon.length(); ++i) {
				if ((Character.isDigit(telefon.charAt(i))) == false) {
					errors.rejectValue("telefon", "nonNumeric", "Mùže obsahovat pouze èíslice");
					break;
				}
			}
		}
	}
	
	private boolean jeMailOk(String mail) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(mail);
		return matcher.matches();
	}

}
