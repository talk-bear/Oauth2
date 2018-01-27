package com.qiyun.qy.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;
import com.qiyun.qy.entity.User;

@Controller
@RequestMapping("/")
public class UserController {
	
	@RequestMapping(path = {"/", "home"}, method = RequestMethod.GET)
	public String root() throws URISyntaxException {
		return "home";
	}
	
	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
	        @RequestParam(value = "logout", required = false) String logout,
	        HttpServletRequest request, Model model) {
		
	    if (error != null) {
	    	String secExKey = "SPRING_SECURITY_LAST_EXCEPTION";
			Exception exception = (Exception) request.getSession().getAttribute(secExKey);
			String errorCause = exception.getMessage();
			model.addAttribute("errorCause", errorCause);
	    }

	    if (logout != null) {
	    	model.addAttribute("msg", "You've been logged out successfully.");
	    }

		return "login";
	}
	
	@RequestMapping(path = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest req) {
		
		String error = req.getParameter("error");
		if (!StringUtils.isEmpty(error)) {
			throw new UsernameNotFoundException("register error ");
		}
		
		return "register";
	}

	@RequestMapping(path = "register", method = RequestMethod.POST)
	public void register(User user, HttpServletResponse resp) {
		try {
			resp.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
