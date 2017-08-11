package com.togo.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@RestController
public class QAController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/qa")
    public Map qa(HttpServletRequest request, @RequestBody(required = false) String body) throws UnsupportedEncodingException {
        if(request.getMethod().equals(HttpMethod.GET.toString())) {

            String url = "http://gamesdk-qa.biligame.com/qa?tohost=";
            String[] strs = request.getQueryString().split("8080");
            String host = strs[0].replace("tohost=", "");
            String queryString = strs[1];
            url += host + "8080" + queryString;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://gamesdk-qa.biligame.com/qa");
            builder.queryParam("tohost", host + "8080" + queryString);
            return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.valueOf(request.getMethod()), null, Map.class).
                    getBody();
        } else {
            String url = "http://gamesdk-qa.biligame.com/qa?tohost=";
            String[] strs = URLDecoder.decode(body,"UTF-8").split("8080");
            String host = strs[0].replace("tohost=", "");
            String queryString = strs[1];
            url += host + "8080" + queryString;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://gamesdk-qa.biligame.com/qa");
            builder.queryParam("tohost", host + "8080" + queryString.replaceFirst("&","?"));
            return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.valueOf(request.getMethod()), null, Map.class).
                    getBody();
        }
    }

    @RequestMapping("/qa_post")
    public Map qaPost(HttpServletRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity e = new HttpEntity(ImmutableMap.of("tohost", request.getParameter("tohost")), headers);

        return restTemplate.postForObject("http://gamesdk-qa.biligame.com/qa",e,Map.class);
    }
}
