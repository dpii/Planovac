package cz.uhk.planovac;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelMap uzivateleHandler() {
		Uzivatele uzivatele = new Uzivatele();
		uzivatele.nactiUzivateleList().addAll(this.planovac.vemUzivatele());
		return new ModelMap(uzivatele);
	}
	
	@RequestMapping("/uzivatele/{uzivatelId}")
	public ModelAndView ownerHandler(@PathVariable("uzivatel") int uzivatelId) {
		ModelAndView mav = new ModelAndView("uzivatel/zobraz");
		mav.addObject(this.planovac.nactiUzivatele(uzivatelId));
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
