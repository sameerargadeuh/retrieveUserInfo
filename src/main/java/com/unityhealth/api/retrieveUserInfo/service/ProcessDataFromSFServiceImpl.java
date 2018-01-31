/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.api.retrieveUserInfo.domain.IUserReportRepository;
import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.Dummy;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sameer S Argade
 */
@Service
public class ProcessDataFromSFServiceImpl implements ProcessDataFromSFService {

    private Logger log = LoggerFactory.getLogger(ProcessDataFromSFServiceImpl.class);
    
    @Autowired
    private IUserReportRepository userReportRepository;

    ProcessDataFromSFServiceImpl(IUserReportRepository userReportRepository) {
        this.userReportRepository = userReportRepository;
    }

    @Override
    public void validateUsersLit(List<Dummy> dataList) {
          ObjectMapper objectMapper = new ObjectMapper();
        //file = new File("E:\\biocueticalsload\\product.json");
       File file = new File("E:\\retrieveUserInfo\\FileFromSF130-14-2018.json");
        
        try {
            Dummy singlePage = new Dummy();
           singlePage = objectMapper.readValue(file,Dummy.class);
             dataList = new ArrayList<Dummy>();
        dataList.add(singlePage);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       Integer foundByEmail = 0;
       Integer foundByBusinessEmail =0;
       Integer foundByPricelineStores = 0;
       Integer foundInNonPriceLinseStores = 0;
        User[] userList = null;
        for (Dummy data : dataList) {
            userList = data.getD().getResults();
            for (User user : userList) {
                //find user by email id 
                String email = null;
                String storeName = null;
                String businessEmail = null;
                
                if(user.getEmail() != null  && user.getEmail().lastIndexOf("_1") != -1)
                        email = user.getEmail().substring(0, user.getEmail().lastIndexOf("_1")).trim();
                else  if(user.getEmail() != null)
                     email = user.getEmail().trim();
                
                  log.debug(".....................user ................................. " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() );
                if(user.getLocation() != null && user.getLocation().lastIndexOf("(") !=-1){
                    storeName = user.getLocation().substring(0, user.getLocation().lastIndexOf("(")).trim();
                }else  if(user.getLocation() != null)
                    storeName = user.getLocation().trim();
                
                log.debug(".....................storeName................................. " + storeName );
                
                Tblaccount account = userReportRepository.findByVUsernameAndVStatus(email,"active");
                if (account != null) { // if user is found in ithera db the user is already present in the db
                    log.debug(".....................user found by email ................................. "  + email );
                    account.setApiUserId(user.getUserId());

                } else {
                   //  log.debug(".....................user not found by email ................................. " + email );
                     if(user.getCustom12() != null  && user.getCustom12().lastIndexOf("_1") != -1)
                        businessEmail = user.getCustom12().substring(0, user.getCustom12().lastIndexOf("_1"));
                else 
                     businessEmail = user.getCustom12();
                    account = userReportRepository.findByVUsernameAndVStatus(businessEmail,"active");
                    
                    if (account != null) {
                         log.debug(".....................user found by businessEmail ................................. " + businessEmail );
                        account.setApiUserId(user.getUserId());

                    } else {
                       //  log.debug(".....................user not found by businessEmail ................................. " + businessEmail );
                        account = userReportRepository.findByVEmailAndVStatus(email,"active");
                        if (account != null) {
                            log.debug(".....................user found by businessEmail ................................. " + businessEmail );
                            account.setApiUserId(user.getUserId());

                        } else {
                             if(user.getCustom12() != null  && user.getCustom12().lastIndexOf("_1") != -1)
                        businessEmail = user.getCustom12().substring(0, user.getCustom12().lastIndexOf("_1"));
                else 
                     businessEmail = user.getCustom12();
                            account = userReportRepository.findByVEmailAndVStatus(businessEmail,"active");
                            if (account != null) {
                                log.debug(".....................user found by businessEmail ................................. " + businessEmail );
                                account.setApiUserId(user.getUserId());

                            }else{
                                //log.debug(".....................use not  found by businessEmail ................................. " + businessEmail );
                                List<Integer> groupIDs = new ArrayList<Integer>();
                                groupIDs.add(79);
                                groupIDs.add(199);
                                 account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdInAndVStatus(user.getFirstName(), user.getLastName(),storeName,groupIDs,"active");
                                  if (account != null) {
                                
                                      log.debug("PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" +  account.getVEmail());
                            }else{
                                      account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdNotInAndVStatus(user.getFirstName(), user.getLastName(),storeName,groupIDs,"active");
                                       if (account != null) {
                                
                                      log.debug(" Not a PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" +  account.getVEmail());
                            }else{
                                           account = userReportRepository.findByVFirstNameAndVLastNameAndVStatus(user.getFirstName(), user.getLastName(),"active");
                                            if (account != null) {
                                
                                      log.debug("Not in the Store they suggest " + account.getVEmail());
                            }
                                       }
                                  }
                                 
                            }
                        }
                    }
                }
            }

        }
    }
    public static void main(String args[]){
        
    }

}
