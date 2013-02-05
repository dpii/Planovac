
package cz.uhk.planovac.web;

import java.util.ArrayList;
import java.util.Collection;

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
import cz.uhk.planovac.Skupina;
import cz.uhk.planovac.Udalost;
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
		new UzivatelValidator().validate(uzivatel, result, this.planovac);
		if (result.hasErrors()) {
			return "novyuzivatel";
		}
		else {
			uzivatel.setRole("ROLE_USER");
			uzivatel.setPovolen(true);
			Collection<Udalost> seznam1 = new ArrayList<Udalost>();
			uzivatel.setSeznamUdalosti(seznam1);
			Collection<Skupina> seznam2 = new ArrayList<Skupina>();
			uzivatel.setSeznamSkupin(seznam2);
			Collection<Udalost> seznam3 = new ArrayList<Udalost>();
			uzivatel.setVedeneUdalosti(seznam3);
			Collection<Skupina> seznam4 = new ArrayList<Skupina>();
			uzivatel.setVedeneSkupiny(seznam4);
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
		//kdyby si zmìnil login
		String prihlaseny = SecurityContextHolder.getContext().getAuthentication().getName();
		if(prihlaseny.compareTo(uzivatel.getLogin())==0)
			new UzivatelValidator().validate(uzivatel, result);
		else
			new UzivatelValidator().validate(uzivatel, result, this.planovac);
		
		if (result.hasErrors()) {
			return "novyuzivatel";
		}
		else {
			uzivatel.setRole("ROLE_USER");
			uzivatel.setPovolen(true);
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			//kdyby si zmìnil login
			if(prihlaseny.compareTo(uzivatel.getLogin())==0)
				return "redirect:/uzivatel";
			else
				return "redirect:/logout";
		}
	}


}
