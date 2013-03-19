
package cz.uhk.planovac.web;

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
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.validation.SkupinaValidator;

@Controller
@SessionAttributes(types = Skupina.class)
public class SkupinaForm {
	
	private final Planovac planovac;


	@Autowired
	public SkupinaForm(Planovac planovac) {
		this.planovac = planovac;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("idSkupiny");
	}

	@RequestMapping(value = "/novaskupina", method = RequestMethod.GET)
	public String setupForm(Model model) {
		Skupina skupina = new Skupina();
		model.addAttribute(skupina);
		return "novaskupina";
	}

	@RequestMapping(value = "/novaskupina", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Skupina skupina, BindingResult result, SessionStatus status) {
		new SkupinaValidator().validate(skupina, result);
		if (result.hasErrors()) {
			return "novaskupina";
		}
		else {
			Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
			skupina.setVedouci(uzivatel);
			uzivatel.getSeznamSkupin().add(skupina);
			this.planovac.ulozUzivatele(uzivatel);
			status.setComplete();
			return "redirect:/uzivatel";
		}
	}
	
	@RequestMapping(value = "/skupiny/{idSkupiny}/upravit", method = RequestMethod.GET)
	public String setupFormUprava(Model model, @PathVariable("idSkupiny") int idSkupiny) {
		Skupina skupina = planovac.nactiSkupinu(idSkupiny);
		//skupina.setVedouci(planovac.nactiVedoucihoSkupiny(idSkupiny));
		if(skupina.getVedouci().getLogin().compareToIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName())!=0)
			return "redirect:/denied";
		model.addAttribute(skupina);
		return "novaskupina";
	}

	@RequestMapping(value = "/skupiny/{idSkupiny}/upravit", method = RequestMethod.PUT)
	public String processSubmitUprava(@ModelAttribute Skupina skupina, BindingResult result, SessionStatus status, @PathVariable("idSkupiny") int idSkupiny) {
		new SkupinaValidator().validate(skupina, result);
		if (result.hasErrors()) {
			return "novaskupina";
		}
		else {
			skupina.setSeznamClenu(planovac.nactiUzivateleDleSkupiny(idSkupiny));
			skupina.setSeznamUdalosti(planovac.nactiUdalostiDleSkupiny(idSkupiny));
			Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
			skupina.setVedouci(uzivatel);
			//uzivatel.getSeznamUdalosti().add(udalost);
			//this.planovac.smazUdalost(idUdalosti);
			//this.planovac.ulozUzivatele(uzivatel);
			this.planovac.ulozSkupinu(skupina);
			status.setComplete();
			return "redirect:/skupiny/"+skupina.getIdSkupiny();
		}
	}
	
	@RequestMapping(value = "/skupiny/{idSkupiny}/upravit", method = RequestMethod.DELETE)
	public String smazatSkupinu(@PathVariable("idSkupiny") int idSkupiny) {
		this.planovac.smazSkupinu(idSkupiny);
		return "redirect:/uzivatel";
	}
	
	@RequestMapping(value = "/skupiny/{idSkupiny}/pridatSe")
	public String pridatSe(@PathVariable("idSkupiny") int idSkupiny) {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		this.planovac.pridatUzivateleDoSkupiny(uzivatel.getIdUzivatele(),idSkupiny);
		return "redirect:/skupiny/"+idSkupiny;
	}
	
	@RequestMapping(value = "/skupiny/{idSkupiny}/odebratSe")
	public String odebratSe(@PathVariable("idSkupiny") int idSkupiny) {
		Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		this.planovac.odebratUzivateleZeSkupiny(uzivatel.getIdUzivatele(),idSkupiny);
		return "redirect:/skupiny/"+idSkupiny;
	}

}