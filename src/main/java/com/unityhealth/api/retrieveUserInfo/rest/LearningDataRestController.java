/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sameer S Argade
 */
@RestController
@RequestMapping("/api/learning")
public class LearningDataRestController {
    @Autowired
    private OAuth2RestTemplate template;
    
     @Value("${secured.service.url}")
    private String endpoint;
     
     @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String getMessageFromSecuredService(){
        ResponseEntity<String> entity = template.getForEntity(endpoint, String.class);
        return entity.getBody();
    }
    
}
