package com.lshlmy.study.controller;




import java.util.UUID;

import com.lshlmy.study.common.annotation.CurrentUser;
import com.lshlmy.study.common.http.HttpRequestWrapper;
import com.lshlmy.study.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description 用户Action
 * @author lshlmy
 */
@Controller
@RequestMapping("/")
public class UserController {
	private final static Logger LOGGER = LogManager.getLogger(UserController.class);
	
	@RequestMapping(value = "index3", method = RequestMethod.GET)
	public @ResponseBody Object index3(HttpRequestWrapper request, @CurrentUser User user) {
		request.getToken();
		return user;
	}

	@RequestMapping(value = "index")
	public String index(@CurrentUser User user,Model model) {
		model.addAttribute(user); 
		LOGGER.info(user);
		//LOGGER.error(user);
		return "user/indexUser";
	}

	@RequestMapping(value = "index2", method = RequestMethod.GET)
	public String index2(HttpRequestWrapper request) {
		return "user/index";
	}

	@RequestMapping("loginPage")
	public String toLoginPage() {
		return "loginPage";
	}
	
	

	

}
