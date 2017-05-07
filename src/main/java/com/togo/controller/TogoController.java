package com.togo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/togo")
public class TogoController {

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
    
    public Map<String,String> getEnv() {
	Map<String,String> configMap = new HashMap<>();
	configMap.put("jenkins", "http://118.89.183.194:8080/");
	configMap.put("togo_project", "http://118.89.183.194:8080/togo/hello");
	return configMap;
    }
}
