package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/controller")
public class CompareController {
	@RequestMapping(method = RequestMethod.GET)
	public String controller(ModelMap model) {
		model.addAttribute("title", "Compare - Kainos internship funds app");
		
		
		return "controller";  
		
	
	}
}
