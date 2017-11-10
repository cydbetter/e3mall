package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/index")
	public String index() {
		
		System.out.println(666);
		
		
		return "index";
	}
	
}
