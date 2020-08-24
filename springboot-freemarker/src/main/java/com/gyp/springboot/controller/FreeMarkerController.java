package com.gyp.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FreeMarkerController {
    
	@RequestMapping("/index")
	public String toIndex() {
		return "Index";
	}
}