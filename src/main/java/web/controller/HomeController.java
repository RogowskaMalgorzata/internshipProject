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
public class HomeController {
	
	//spring context
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    
    //beans
    FundDAO fundDAO = ctx.getBean("fundDAO", FundDAO.class);
    ConvertService convertService = ctx.getBean("convertService", ConvertService.class);
	

	@RequestMapping("/")
	public String home(ModelMap model) {  
		model.addAttribute("title", "Kainos aplikacja konkursowa");
		
//		List<Fund> fList = convertService.readFromFile();
//		
//		for (Fund fund : fList) {
//			fundDAO.save(fund);
//		}
		
		List<Fund> fundList = fundDAO.getAll();
		model.addAttribute("fundList", fundList);
		
		ctx.close();
		return "home";
	}
	
	
	
}
