package cz.uhk.planovac.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class LoginController {
 
	@RequestMapping(value="/vitejte", method = RequestMethod.GET)
	public String login(ModelMap model, @RequestParam(required=false) String message ) {
		
		model.addAttribute("message", message);
		return "home";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "home";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "home";
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "home";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "redirect:/login?message=Odhlasen";
 
	}
 
}