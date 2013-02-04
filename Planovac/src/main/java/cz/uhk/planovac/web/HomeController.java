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

@Controller
public class HomeController {

	private final Planovac planovac;
	private ManazerUdalosti manazerUdalosti = new ManazerUdalosti();

	@Autowired
	public HomeController(Planovac planovac) {
		this.planovac = planovac;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping("/uzivatele")
	public ModelAndView uzivateleHandler() {
		ModelAndView mav = new ModelAndView("uzivatele");
		mav.addObject("uzivatele", this.planovac.vemUzivatele());
		return mav;
	}

	@RequestMapping("/uzivatele/{idUzivatele}")
	public ModelAndView uzivatelHandler(
			@PathVariable("idUzivatele") int idUzivatele) {
		ModelAndView mav;
		Uzivatel uzivatel = this.planovac.nactiUzivatele(idUzivatele);
		String prihlaseny = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (prihlaseny.compareTo(uzivatel.getLogin()) == 0)
			mav = new ModelAndView("redirect:/uzivatel");
		else
			mav = new ModelAndView("jinyUzivatel");
		mav.addObject("uzivatel", uzivatel);
		return mav;
	}

	@RequestMapping("/uzivatel")
	public ModelAndView prihlasenyHandler() {
		ModelAndView mav = new ModelAndView("uzivatel");
		Uzivatel uzivatel = this.planovac
				.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		mav.addObject("uzivatel", uzivatel);
		ArrayList<Udalost> udalosti = new ArrayList<Udalost>(
				manazerUdalosti.seradUdalosti(uzivatel.getSeznamUdalosti()));
		mav.addObject("seznamUdalosti",
				manazerUdalosti.getNAktualnichUdalosti(udalosti, 5));// planovac.nactiUdalostiDleUzivatele(uzivatel.getIdUzivatele()));
		mav.addObject("seznamSkupin", uzivatel.getSeznamSkupin());
		return mav;
	}

	@RequestMapping("/uzivatel/udalosti")
	public ModelAndView vsechnyUdalostiHandler() {
		ModelAndView mav = new ModelAndView("udalosti");
		Uzivatel uzivatel = this.planovac
				.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		mav.addObject("uzivatel", uzivatel);
		ArrayList<Udalost> udalosti = new ArrayList<Udalost>(
				manazerUdalosti.seradUdalosti(uzivatel.getSeznamUdalosti()));
		mav.addObject("seznamUdalosti", udalosti);
		mav.addObject("nadpis", "Všechny vaše události");
		mav.addObject("verejne", false);
		return mav;
	}

	@RequestMapping("/udalosti")
	public ModelAndView udalostiHandler() {
		ModelAndView mav = new ModelAndView("udalosti");
		ArrayList<Udalost> udalosti = new ArrayList<Udalost>(
				manazerUdalosti.seradUdalosti(planovac.vemVerejneUdalosti()));
		mav.addObject("seznamUdalosti",
				manazerUdalosti.getNAktualnichUdalosti(udalosti, 100));
		mav.addObject("nadpis", "Nejbližší veøejné události");
		mav.addObject("verejne", true);
		return mav;
	}

	@RequestMapping("/udalost/{idUdalosti}")
	public ModelAndView udalostHandler(@PathVariable("idUdalosti") int idUdalosti) {
		ModelAndView mav = new ModelAndView("udalost");
		boolean opravneni = false;
		int neniVeSkupine = 0;
		Udalost udalost = this.planovac.nactiUdalost(idUdalosti);
		Uzivatel vlastnik = udalost.getVlastnikUz();
		
		
		String prihlasenyLogin = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		Uzivatel prihlaseny = this.planovac
				.nactiUzivatelePodleLoginu(prihlasenyLogin);
				
		/*
		if (prihlasenyLogin.compareToIgnoreCase("anonymousUser") != 0) {
			if (skupina.isVerejna())
				neniVeSkupine = 1;
			
		}*/
			
			if (vlastnik.getIdUzivatele().equals(prihlaseny.getIdUzivatele()))
				opravneni = true;

		mav.addObject("udalost", udalost);
		mav.addObject("opravneni", opravneni);
		
		return mav;
	}

	@RequestMapping("/skupiny/{idSkupiny}")
	public ModelAndView skupinaHandler(@PathVariable("idSkupiny") int idSkupiny) {
		ModelAndView mav = new ModelAndView("skupina");
		boolean opravneni = false;
		int neniVeSkupine = 0;
		Skupina skupina = this.planovac.nactiSkupinu(idSkupiny);
		Uzivatel vedouci = skupina.getVedouci();
		Collection<Uzivatel> cleni = planovac
				.nactiUzivateleDleSkupiny(idSkupiny);
		// Collection<Uzivatel> cleni = skupina.getSeznamClenu(); //chyba:
		// "collection is not associated with any session"
		skupina.setSeznamClenu(cleni);
		String prihlasenyLogin = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (prihlasenyLogin.compareToIgnoreCase("anonymousUser") != 0) {
			if (skupina.isVerejna())
				neniVeSkupine = 1;
			Uzivatel prihlaseny = this.planovac
					.nactiUzivatelePodleLoginu(prihlasenyLogin);
			for (Uzivatel uzivatel : cleni) {
				if (uzivatel.getIdUzivatele().equals(
						prihlaseny.getIdUzivatele()))
					neniVeSkupine = 2;
			}
			if (vedouci.getIdUzivatele().equals(prihlaseny.getIdUzivatele()))
				opravneni = true;

		}

		mav.addObject("cleni", planovac.nactiUzivateleDleSkupiny(idSkupiny));

		Collection<Udalost> seznamUdalosti = planovac
				.nactiUdalostiDleSkupiny(idSkupiny);
		mav.addObject("vedouci", vedouci);
		mav.addObject("seznamUdalosti", seznamUdalosti);
		mav.addObject("skupina", skupina);
		mav.addObject("opravneni", opravneni);
		mav.addObject("neniVeSkupine", neniVeSkupine);
		return mav;
	}

}
