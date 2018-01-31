/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.sf.userModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sameer S Argade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dummy {

   
    /**
     * @return the d
     */
    public Result getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(Result d) {
        this.d = d;
    }

   

  

  
 private Result d   ;
 

 
}
