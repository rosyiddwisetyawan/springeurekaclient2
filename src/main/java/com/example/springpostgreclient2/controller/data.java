package com.example.springpostgreclient2.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class data {

    @Autowired
    EurekaClient eurekaClient;

    @GetMapping(value = "/kuda")
    public String data(){
        Application application = eurekaClient.getApplication("springpostgreclient");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        System.out.println(hostname);
        System.out.println(port);

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://" + hostname + ":" + port + "/data";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        return response.getBody();
    }

}
