package cz.uhk.planovac.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Skupina;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.Vyhledavani;
 
@Controller
@SessionAttributes(types = Vyhledavani.class)
public class TerminyController {
	
	private final Planovac planovac;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
	}

	@Autowired
	public TerminyController(Planovac planovac) {
		this.planovac = planovac;
	}
	
	@ModelAttribute("skupiny")
	public Collection<Skupina> nactiSkupiny() {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		return uzivatel.getVedeneSkupiny();
	}
 
	@RequestMapping(value = "/vyhledatTermin", method = RequestMethod.GET)
	public String setupForm(Model model) {
		Vyhledavani vyhledavani = new Vyhledavani();
		vyhledavani.setOdData(new Date());
		vyhledavani.setDoData(new Date());
		vyhledavani.setOdHodiny(0);
		vyhledavani.setDoHodiny(24);
		vyhledavani.setDelka(3000);
		model.addAttribute(vyhledavani);
		return "vyhledatTermin";
	}

	@RequestMapping(value = "/vyhledatTermin", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("vyhledavani") Vyhledavani vyhledavani, BindingResult result, Model model) {
		vyhledavani.setDelka(vyhledavani.getDelka()*60);
		ArrayList<Udalost> vysledky = vyhledavani.provedHledani(SecurityContextHolder.getContext().getAuthentication().getName(),this.planovac);
		model.addAttribute("vysledky", vysledky);
		return "vyhledaneTerminy";
	}
 
}