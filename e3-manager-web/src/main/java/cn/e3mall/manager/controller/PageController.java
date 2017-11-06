package cn.e3mall.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 由于输入的参数就是页面文件的名字，当返回到视图解析器，从而能够找到对应的页面
	 * @param page
	 * @return page
	 */
	@RequestMapping("{page}")
	public String page(@PathVariable String page) {
		
		return page;
	}
	
}
