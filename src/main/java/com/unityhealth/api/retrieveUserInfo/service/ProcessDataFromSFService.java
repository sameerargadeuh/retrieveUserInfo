/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.service;

import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.Dummy;
import java.util.List;

/**
 *
 * @author Sameer S Argade
 */
public interface ProcessDataFromSFService {
    
    //void validateUsersLit(List<Dummy> dataList);
    
    void validateUsersLit(String jsonFileName,String excelFileName);
     public String joinUser( Tblaccount in) ;
     public void addNewUSersToBreeze();
}
