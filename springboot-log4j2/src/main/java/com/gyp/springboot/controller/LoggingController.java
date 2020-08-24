package com.gyp.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
	
	private static Logger logger = LogManager.getLogger(LoggingController.class);
    
	@RequestMapping("/index")
	public String Loging() {
		
		logger.debug("111111");
		logger.warn("222222222");
		logger.error("333333333333");
		logger.fatal("44444444444444");
		
		return "asd";
	}
	
	
	
	

    

    

}
