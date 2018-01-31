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
public class Result {
     /**
     * @return the __next
     */
     @JsonProperty("__next")
    public String getNext() {
        return __next;
    }

    /**
     * @param __next the __next to set
     */
    public void setNext(String __next) {
        this.__next = __next;
    }


    /**
     * @return the results
     */
    public User[] getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(User[] results) {
        this.results = results;
    }
    private User[] results;
     private String __next;
}
