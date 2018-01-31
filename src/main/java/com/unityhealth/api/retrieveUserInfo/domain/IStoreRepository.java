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
public interface IStoreRepository extends JpaRepository<Store,Integer> {
    
    Store findByid(Integer storeID);
    List<Store> findByStoreNameContainingOrVLocAddress1ContainingOrVLocAddress2ContainingOrVLocLocality1ContainingOrVLocLocality2ContainingOrPostCodeContainingOrStoreRegionContainingOrVLocCountryContaining(String term1,String term2,String term3,String term4,String term5,String term6,String term7,String term8);
    List<Store> findByGroup_IdAndStoreRegionOrderByStoreName(Integer id,String region);

     @Query("SELECT s,g FROM OwnerGroupStoreAssoc o,Group g, Store s  WHERE" +
" o.iStoreID = s.id  AND s.group.id = g.id AND o.iOwnerGroupID=?1 order by g.vName,s.storeName ")
     public List<Object[]> findStoresByOwnerGroupId(Integer iOwnerGroupID);
     
     
     
//      SELECT s.iID, s.iGroupID, g.vName AS 's.vGroupName', s.iType, s.vLocName, s.vLocAddress1, s.vLocAddress2,
//                 s.vLocLocality1, s.vLocLocality2, s.vLocRegion, s.vLocPostCode, s.vLocCountry, 
//                 s.vPhoneNumber, s.vFaxNumber, s.vCodeIMS, s.vAccountCode, s.iNumStaff, 0 AS 'n.iNumAccounts' 
//                 FROM tblterritoriessupplier ts, tblterritorystoressupplier tss, tblstores s, tblgroups g 
//                 WHERE s.iGroupID=g.iID AND ts.iID=tss.iTerritoryID AND tss.iStoreID=s.iID  AND ts.iID=? 
//                 ORDER BY g.vName, s.vLocName 
//       @Query("SELECT s FROM Territoriessupplier ts,TerritoryStoresSupplier tss, Group g,Store s  WHERE " +
//"  s.group.id = g.id AND ts.iID = tss.iTerritoryID AND tss.iStoreID=s.id AND ts.iID=?  order by g.vName,s.storeName")
//     public List<Store> findStoresByTerritoryId(Integer trId);
     
    

}

 

//s.vLocName LIKE ? OR s.vLocAddress1 LIKE ? OR s.vLocAddress2 LIKE ? OR "
//                + "   s.vLocLocality1 LIKE ? OR s.vLocLocality2 LIKE ? OR s.vLocPostCode LIKE ? OR "
//                + "   s.vLocRegion LIKE ? OR s.vLocCountry LIKE 