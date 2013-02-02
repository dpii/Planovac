
package cz.uhk.planovac.web;

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
import org.springframework.web.bind.support.SessionStatus;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.validation.UdalostValidator;

@Controller
@RequestMapping("/novaudalost")
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

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		Udalost udalost = new Udalost();
		udalost.setZacatek(new Date());
		udalost.setKonec(new Date());
		model.addAttribute(udalost);
		return "novaudalost";
	}

	@RequestMapping(method = RequestMethod.POST)
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

}

/*SessionFactory sf = HibernateUtil.getSessionFactory();
Session session = sf.openSession();
session.beginTransaction();
 
session.save(udalost);
 
session.getTransaction().commit();
session.close();*/
