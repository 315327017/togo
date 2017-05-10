package com.togo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    
    @GetMapping("/env")
    public Map<String,String> getEnv() {
	Map<String,String> configMap = new HashMap<>();
	configMap.put("togo_project", "http://www.luang.me/togo/hello");
	return configMap;
    }
    
    @Bean
    RestTemplate restTemplate(){
      return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/bilibili/user/{id}")
    @SuppressWarnings("all")
    public Map<String,Object> getBilibiliUser(@PathVariable String id) {
	return restTemplate.getForObject("http://account.bilibili.com/api/member/getInfoByMid?mid={id}", Map.class, id);
    }

}
