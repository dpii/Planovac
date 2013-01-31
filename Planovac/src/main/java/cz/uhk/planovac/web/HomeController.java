package cz.uhk.planovac.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.Uzivatele;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private final Planovac planovac;


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
			mav = new ModelAndView("uzivatel");
		else
			mav = new ModelAndView("jinyUzivatel");
		mav.addObject("uzivatel",uzivatel);
		return mav;
	}
	
	@RequestMapping("/uzivatel")
	public ModelAndView uzivatelHandler() {
		ModelAndView mav = new ModelAndView("uzivatel");
		mav.addObject("uzivatel", this.planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName()));
		return mav;
	}
	
	
	
	// T0D0 udalosti uzivatelu
	
	//	@RequestMapping(value="/uzivatele/*/udalosti/{udalostId}/???XXX", method=RequestMethod.GET)
	//		public ModelAndView visitsHandler(@PathVariable int petId) {
	//		ModelAndView mav = new ModelAndView("visits");
	//	mav.addObject("visits", this.planovac.nactiUdalost(idUdalosti));
	//	return mav;
	//	}
	
	
}
