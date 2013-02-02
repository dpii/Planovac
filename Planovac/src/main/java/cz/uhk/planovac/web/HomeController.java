package cz.uhk.planovac.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.uhk.planovac.ManazerUdalosti;
import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Skupina;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private final Planovac planovac;
	private ManazerUdalosti manazerUdalosti = new ManazerUdalosti();


	@Autowired
	public HomeController(Planovac planovac) {
		this.planovac = planovac;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/uzivatele")
	public ModelAndView uzivateleHandler() {
		ModelAndView mav = new ModelAndView("uzivatele");
		mav.addObject("uzivatele", this.planovac.vemUzivatele());
		return mav;
	}
	
	@RequestMapping("/uzivatele/{idUzivatele}")
	public ModelAndView ownerHandler(@PathVariable("idUzivatele") int idUzivatele) {
		ModelAndView mav;
		Uzivatel uzivatel = this.planovac.nactiUzivatele(idUzivatele);
		String prihlaseny = SecurityContextHolder.getContext().getAuthentication().getName();
		if(prihlaseny.compareTo(uzivatel.getLogin())==0)
			mav = new ModelAndView("redirect:/uzivatel");
		else
			mav = new ModelAndView("jinyUzivatel");
		mav.addObject("uzivatel",uzivatel);
		return mav;
	}
	
	@RequestMapping("/uzivatel")
	public ModelAndView uzivatelHandler() {
		ModelAndView mav = new ModelAndView("uzivatel");
		Uzivatel uzivatel = this.planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
		mav.addObject("uzivatel", uzivatel);
		ArrayList<Udalost> udalosti = new ArrayList<Udalost>(manazerUdalosti.seradUdalosti(uzivatel.getSeznamUdalosti()));
		mav.addObject("seznamUdalosti", manazerUdalosti.getNAktualnichUdalosti(udalosti, 5));//planovac.nactiUdalostiDleUzivatele(uzivatel.getIdUzivatele()));
		mav.addObject("seznamSkupin", uzivatel.getSeznamSkupin());
		return mav;
	}
	
	
	
	// T0D0 udalosti uzivatelu
	
	//	@RequestMapping(value="/uzivatele/*/udalosti/{udalostId}/???XXX", method=RequestMethod.GET)
	//		public ModelAndView visitsHandler(@PathVariable int petId) {
	//		ModelAndView mav = new ModelAndView("visits");
	//	mav.addObject("visits", this.planovac.nactiUdalost(idUdalosti));
	//	return mav;
	//	}
	
	@RequestMapping("/skupiny/{idSkupiny}")
	public ModelAndView skupinaHandler(@PathVariable("idSkupiny") int idSkupiny) {
		ModelAndView mav  = new ModelAndView("skupina");
		
		Skupina skupina = this.planovac.nactiSkupinu(idSkupiny);
		
		Collection<Uzivatel> cleni = planovac.nactiUzivateleDleSkupiny(idSkupiny);
		//Collection<Uzivatel> cleni = skupina.getSeznamClenu(); //chyba: "collection is not associated with any session"
		
		skupina.setSeznamClenu(cleni);
		mav.addObject("cleni", planovac.nactiUzivateleDleSkupiny(idSkupiny));
		
		Uzivatel vedouci = skupina.getVedouci();
		mav.addObject("vedouci", vedouci);
		
		mav.addObject("skupina",skupina);
		return mav;
	}
	
	
}
