package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompareController {
	@RequestMapping("/compare")
	public String controller(ModelMap model) {
		model.addAttribute("title", "Compare - Kainos internship funds app");
		
		
		return "compare";  
		
	
	}
}
