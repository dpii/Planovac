package cz.uhk.planovac.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Uzivatel;

public class UzivatelValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	//nezji��uje dostupnost loginu
	public void validate(Uzivatel uzivatel, Errors errors) {
		if (!StringUtils.hasLength(uzivatel.getLogin())) {
			errors.rejectValue("login", "required", "Povinn� pole");
		}
		if (!StringUtils.hasLength(uzivatel.getHeslo_hash())) {
			errors.rejectValue("heslo_hash", "required", "Povinn� pole");
		}
		if (!StringUtils.hasLength(uzivatel.getEmail())) {
			errors.rejectValue("email", "required", "Povinn� pole");
		}
		if (!jeMailOk(uzivatel.getEmail())) {
			errors.rejectValue("email", "email" ,"�patn� form�t");
		}

		String telefon = uzivatel.getTelefon();
		if(StringUtils.hasLength(telefon))
		{
			for (int i = 0; i < telefon.length(); ++i) {
				if ((Character.isDigit(telefon.charAt(i))) == false) {
					errors.rejectValue("telefon", "nonNumeric", "M��e obsahovat pouze ��slice");
					break;
				}
			}
		}
	}
	//zji��uje dostupnost loginu
	public void validate(Uzivatel uzivatel, Errors errors, Planovac planovac) {
		
		if (!planovac.jeLoginVolny(uzivatel.getLogin())) {
			errors.rejectValue("login", "duplicity", "Tento login u� je obsazen�, zvolte jin�");
		}
		validate(uzivatel, errors);
	}
	
	private boolean jeMailOk(String mail) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(mail);
		return matcher.matches();
	}

}
