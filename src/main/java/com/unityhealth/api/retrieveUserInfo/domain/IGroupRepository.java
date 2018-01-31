/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Sameer S Argade
 */
public interface IGroupRepository extends JpaRepository<Group,Integer >{
 public List<Group> findAllByOrderByBDisplayFirstDesc();  

// @Query("")
 public List<Group> findByIdInOrVParentCompanyContainingOrderByBDisplayFirstDesc(List<Integer> storeIDList,String clientName); 
 public List<Group> findByIdInOrVParentCompanyContainingAndIdNotInOrderByBDisplayFirstDesc(List<Integer> storeIDList,String clientName,List<Integer> groupIDs); 
 int countByvType(String vType);
 
}
