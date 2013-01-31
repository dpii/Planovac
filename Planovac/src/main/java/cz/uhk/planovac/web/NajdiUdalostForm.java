package cz.uhk.planovac.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Udalost;
import cz.uhk.planovac.Uzivatel;

@Controller
public class NajdiUdalostForm {


		private final Planovac planovac;

		@Autowired
		public NajdiUdalostForm(Planovac planovac) {
			this.planovac = planovac;
		}

		@InitBinder
		public void setAllowedFields(WebDataBinder dataBinder) {
			dataBinder.setDisallowedFields("id");
		}

		@RequestMapping(value = "/udalost/hledat", method = RequestMethod.GET)
		public String setupForm(Model model) {
			model.addAttribute("udalost", new Udalost());
			return "udalost/hledat";
		}

		@RequestMapping(value = "/udalost", method = RequestMethod.GET)
		public String processSubmit(Udalost udalost, BindingResult result, Model model) {

			// allow parameterless GET request for /owners to return all records
			if (udalost.getNazev() == null) {
				udalost.setZacatek(null); // empty string signifies broadest possible search
			}

			// find owners by last name
			
			Collection<Udalost> results = this.planovac.nactiUdalostiDleZacatku(udalost.getZacatek());
			if (results.size() < 1) {
				// no owners found
				result.rejectValue("zacatek", "nenalezeno", "nenalezeno");
				return "udalosti/hledat";
			}
			if (results.size() > 1) {
				// multiple owners found
				model.addAttribute("selections", results);
				return "udalosti/list";
			}
			else {
				// 1 owner found
				udalost = results.iterator().next();
				return "redirect:/udalosti/" + udalost.getIdUdalosti();
			}
		}

	}  
