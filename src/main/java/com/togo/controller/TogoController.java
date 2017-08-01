package com.togo.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
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

    private static final ImmutableMap<String,String> channelMap = new ImmutableMap.Builder<String,String>().put("UC九游","sdk.g.uc.cn")
            .put("百度","querysdkapi.91.com")
            .put("360","openapi.360.cn")
            .build();

    @GetMapping("/channels")
    public Map getUosdkChannelInfo() {

        System.out.println(1);

        channelMap.forEach((String channelName, String hostString) -> {
            try {
                boolean isReachable = InetAddress.getByName(hostString).isReachable(1000);
                String info = String.format(channelName + "%s", "\t ping:" + isReachable);
                LOGGER.info(info);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

        return null;
    }

}
