/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sameer S Argade
 */
public interface ICoursesRepository  extends JpaRepository<Tblcourses,Integer> {
    
    Tblcourses findByVUrl(String vUrl);
    Tblcourses findByVUrlContaining(String vUrl);
}
