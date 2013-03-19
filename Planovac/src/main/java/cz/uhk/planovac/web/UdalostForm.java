
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Skupina;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.validation.UdalostValidator;

@Controller
@SessionAttributes(types = Udalost.class)
public class UdalostForm {
	
	private final Planovac planovac;


	@Autowired
	public UdalostForm(Planovac planovac) {
		this.planovac = planovac;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("idUdalosti");
	}
	
	@ModelAttribute("skupiny")
	public Collection<Skupina> nactiSkupiny() {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		Collection<Skupina> skupiny = new ArrayList<Skupina>();
		skupiny.add(new Skupina());
		skupiny.addAll(uzivatel.getVedeneSkupiny());
		return skupiny;
	}

	@RequestMapping(value = "/novaudalost", method = RequestMethod.GET)
	public String setupForm(Model model) {
		Udalost udalost = new Udalost();
		udalost.setZacatek(new Date());
		udalost.setKonec(new Date());
		model.addAttribute(udalost);
		return "novaudalost";
	}

	@RequestMapping(value = "/novaudalost", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Udalost udalost, BindingResult result, SessionStatus status) {
		new UdalostValidator().validate(udalost, result);
		if (result.hasErrors()) {
			return "novaudalost";
		}
		else {
			Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
			udalost.setVlastnikUz(uzivatel);
			uzivatel.getSeznamUdalosti().add(udalost);
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			return "redirect:/uzivatel";
		}
	}
	
	@RequestMapping(value = "/udalost/{idUdalosti}/upravit", method = RequestMethod.GET)
	public String setupFormUprava(Model model, @PathVariable("idUdalosti") int idUdalosti) {
		Udalost udalost = planovac.nactiUdalost(idUdalosti);
		udalost.setVlastnikUz(planovac.nactiVlastnikaUdalosti(idUdalosti));
		if(udalost.getVlastnikUz().getLogin().compareToIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName())!=0)
			return "redirect:/denied";
		model.addAttribute(udalost);
		return "novaudalost";
	}

	@RequestMapping(value = "/udalost/{idUdalosti}/upravit", method = RequestMethod.PUT)
	public String processSubmitUprava(@ModelAttribute Udalost udalost, BindingResult result, SessionStatus status, @PathVariable("idUdalosti") int idUdalosti) {
		new UdalostValidator().validate(udalost, result);
		if (result.hasErrors()) {
			return "novaudalost";
		}
		else {
			udalost.setUcastnici(planovac.nactiUzivateleDleUdalosti(idUdalosti));
			Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
			udalost.setVlastnikUz(uzivatel);
			//uzivatel.getSeznamUdalosti().add(udalost);
			//this.planovac.smazUdalost(idUdalosti);
			//this.planovac.ulozUzivatele(uzivatel);
			this.planovac.ulozUdalost(udalost);
			status.setComplete();
			return "redirect:/udalost/"+udalost.getIdUdalosti();
		}
	}
	
	@RequestMapping(value = "/udalost/{idUdalosti}/upravit", method = RequestMethod.DELETE)
	public String deleteUdalost(@PathVariable("idUdalosti") int idUdalosti) {
		this.planovac.smazUdalost(idUdalosti);
		return "redirect:/uzivatel";
	}
	
	@RequestMapping(value = "/udalost/{idUdalosti}/pridatSe")
	public String pridatSe(@PathVariable("idUdalosti") int idUdalosti) {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		this.planovac.pridatUzivateleKUdalosti(uzivatel.getIdUzivatele(),idUdalosti);
		return "redirect:/udalost/"+idUdalosti;
	}
	
	@RequestMapping(value = "/udalost/{idUdalosti}/odebratSe")
	public String odebratSe(@PathVariable("idUdalosti") int idUdalosti) {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		this.planovac.odebratUzivateleZUdalosti(uzivatel.getIdUzivatele(),idUdalosti);
		return "redirect:/udalost/"+idUdalosti;
	}

}