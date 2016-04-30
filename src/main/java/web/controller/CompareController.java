package web.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.FundDAO;
import importcsv.ConvertService;
import model.Fund;

@Controller
public class CompareController {
	//spring context
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    
    //beans
    FundDAO fundDAO = ctx.getBean("fundDAO", FundDAO.class);
    ConvertService convertService = ctx.getBean("convertService", ConvertService.class);
    
	@RequestMapping("/compare")
	public String controller(ModelMap model) {
		model.addAttribute("title", "Porównaj zysk - Kainos aplikacja konkursowa");
		
		List<Fund> fundList = fundDAO.getAll();
		model.addAttribute("fundList", fundList);
		
		ctx.close();
		
		return "compare";  
		
	
	}
}
