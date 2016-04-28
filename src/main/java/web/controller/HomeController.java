package web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.FundDAO;
import importcsv.ConvertService;
import model.Fund;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//spring context
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
     
    FundDAO fundDAO = ctx.getBean("fundDAO", FundDAO.class);
    ConvertService convertService = ctx.getBean("convertService", ConvertService.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap model) {
		
//		Fund f = new Fund(1, new java.sql.Date(new java.util.Date().getTime()), 20);
//		
//		fundDAO.save(f);
//		
//        Fund f1 = fundDAO.getById(1);
//        System.out.println(f1);
//         
//        f.setValue(30);
//        fundDAO.update(f);
//         

//         
//        //Delete
//        fundDAO.deleteById(1);
//         
//        
		
		List<Fund> fundList = convertService.readFromFile();
		
		for (Fund fund : fundList) {
			fundDAO.save(fund);
		}
		
      
		 Fund f1 = fundDAO.getById(1);
		 System.out.println(f1.getDate());
		
		ctx.close();
		
		model.addAttribute("name", fundList);
		return "home";
	}
	
	
	
}
