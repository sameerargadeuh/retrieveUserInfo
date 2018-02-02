/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Sameer S Argade
 */
public interface IUserReportRepository extends JpaRepository<Tblaccount, Integer>{
    Tblaccount findByVUsername(String vUserName);
    Tblaccount findByVUsernameAndVStatus(String vUserName,String vStatus);
    //Tblaccount findByApiUserId(String apiUserId);
   // Tblaccount findByVUsernameOrByVEmailAndVStatus(String vUserName,String vEmail,String vStatus);
    Tblaccount findByVEmail(String vUserName);
    Tblaccount findByVEmailAndVStatus(String vUserName,String vStatus);
    Tblaccount findByApiUserId(String apiUserId);
    Tblaccount findByVFirstNameAndVLastNameAndVStatus(String vFirstName, String vLastName,String vStatus);
    Tblaccount findByVFirstNameAndVLastNameAndStore_storeNameAndVStatus(String vFirstName, String vLastName,String storeName,String vStatus);
    Tblaccount findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdNotInAndVStatus(String vFirstName, String vLastName,String storeName,List<Integer> groupIDs,String vStatus);
    Tblaccount findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdInAndVStatus(String vFirstName, String vLastName,String storeName,List<Integer> groupIDs,String vStatus);
     @Query("SELECT account FROM Tblaccount account WHERE " +
" (concat(coalesce(trim(account.store.vLocAddress1),''),coalesce(trim(account.store.vLocAddress2),'')) = ?3  AND trim(account.store.vLocLocality1)=?4"
             + " AND trim(account.store.storeRegion)=?5 AND trim(account.store.postCode)=?6 and account.vFirstName=?1 and account.vLastName=?2)"
             + " or "
             + " (trim(account.store.vLocLocality1)=?4"
             + " AND trim(account.store.storeRegion)=?5 AND trim(account.store.postCode)=?6 and account.vFirstName=?1 and account.vLastName=?2) and account.vStatus='active' ")
    Tblaccount findByAddress(String firstName,String lastName,String address,String city,String state,String postCode);
}
