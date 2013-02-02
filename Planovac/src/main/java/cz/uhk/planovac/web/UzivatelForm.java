
package cz.uhk.planovac.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.validation.UzivatelValidator;

@Controller
@SessionAttributes(types = Uzivatel.class)
public class UzivatelForm {	
	
	private final Planovac planovac;


	@Autowired
	public UzivatelForm(Planovac planovac) {
		this.planovac = planovac;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("idUzivatele");
	}

	@RequestMapping(value = "/novyuzivatel", method = RequestMethod.GET)
	public String setupForm(Model model) {
		Uzivatel uzivatel = new Uzivatel();
		model.addAttribute(uzivatel);
		return "novyuzivatel";
	}

	@RequestMapping(value = "/novyuzivatel", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Uzivatel uzivatel, BindingResult result, SessionStatus status) {
		new UzivatelValidator().validate(uzivatel, result);
		if (result.hasErrors()) {
			return "novyuzivatel";
		}
		else {
			uzivatel.setRole("ROLE_USER");
			uzivatel.setPovolen(true);
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			return "redirect:/uzivatele/" + uzivatel.getIdUzivatele();
		}
	}
	
	@RequestMapping(value = "/uzivatel/upravit", method = RequestMethod.GET)
	public String setupFormUprava(Model model) {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		//Uzivatel uzivatel = planovac.nactiUzivatele(2);
		model.addAttribute(uzivatel);
		return "novyuzivatel";
	}

	@RequestMapping(value = "/uzivatel/upravit", method = RequestMethod.PUT)
	public String processSubmitUprava(@ModelAttribute Uzivatel uzivatel, BindingResult result, SessionStatus status) {
		new UzivatelValidator().validate(uzivatel, result);
		if (result.hasErrors()) {
			return "novyuzivatel";
		}
		else {
			uzivatel.setRole("ROLE_USER");
			uzivatel.setPovolen(true);
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			return "redirect:/uzivatel";
		}
	}

}
