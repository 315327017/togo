package com.togo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/togo")
public class TogoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TogoController.class);
    /**
     * simple hello
     * @return
     */
    @GetMapping("/hello")
    public Map<String, String> TogoHello() {
	Map<String, String> helloMap = new HashMap<>();
	helloMap.put("auther", "luang");
	helloMap.put("ctime", "2017-03-12");
	helloMap.put("version", "1.0.0");
	return helloMap;
    }

    @GetMapping("/env")
    public Map<String,String> getEnv() {
	Map<String,String> configMap = new HashMap<>();
	configMap.put("togo_project", "http://www.luang.me/togo/hello");
	return configMap;
    }

}
