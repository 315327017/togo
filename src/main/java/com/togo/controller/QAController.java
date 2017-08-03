package com.togo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@RestController
public class QAController {



    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/qa")
    public Map qa(HttpServletRequest request) throws UnsupportedEncodingException {
        String url = "http://gamesdk-qa.biligame.com/qa?tohost="+URLEncoder.encode(request.getParameter("tohost"),"UTF-8");
       return restTemplate.exchange(url,HttpMethod.valueOf(request.getMethod()),null,Map.class).getBody();
    }
}
