
package cz.uhk.planovac.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.validation.UzivatelValidator;

@Controller
@RequestMapping("novyuzivatel")
@SessionAttributes(types = Uzivatel.class)
public class UzivatelForm {
//test
	
	
	private final Planovac planovac;


	@Autowired
	public UzivatelForm(Planovac planovac) {
		this.planovac = planovac;
	}
	
	//šablona pro výběr (nevyužito)
	@ModelAttribute("mesta")
	public Collection<String> populatePetTypes() {
		Collection<String> seznam = new ArrayList<String>();
		seznam.add("Něco");
		seznam.add("Něco2");
		return seznam;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("idUzivatele");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		Uzivatel uzivatel = new Uzivatel();
		model.addAttribute(uzivatel);
		return "novyuzivatel";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Uzivatel uzivatel, BindingResult result, SessionStatus status) {
		new UzivatelValidator().validate(uzivatel, result);
		if (result.hasErrors()) {
			return "novyuzivatel";
		}
		else {
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			return "redirect:/uzivatele/" + uzivatel.getIdUzivatele();
		}
	}

}
