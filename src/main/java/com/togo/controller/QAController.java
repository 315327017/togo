package com.togo.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        String url = "http://gamesdk-qa.biligame.com/qa?tohost=";
        String[] strs = request.getQueryString().split("8080");
        String host = strs[0].replace("tohost=", "");
        String queryString = strs[1];
        url += host + "8080" + queryString;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://gamesdk-qa.biligame.com/qa");
        builder.queryParam("tohost",host + "8080" + queryString);
        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.valueOf(request.getMethod()), null, Map.class).
                getBody();
    }
}
