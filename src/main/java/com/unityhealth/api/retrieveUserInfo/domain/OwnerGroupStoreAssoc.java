/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sameer S Argade
 */
@Entity
@Table(name = "tblownergroupstoreassoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OwnerGroupStoreAssoc.findAll", query = "SELECT o FROM OwnerGroupStoreAssoc o")
    , @NamedQuery(name = "OwnerGroupStoreAssoc.findByIID", query = "SELECT o FROM OwnerGroupStoreAssoc o WHERE o.iID = :iID")
    , @NamedQuery(name = "OwnerGroupStoreAssoc.findByIOwnerGroupID", query = "SELECT o FROM OwnerGroupStoreAssoc o WHERE o.iOwnerGroupID = :iOwnerGroupID")
    , @NamedQuery(name = "OwnerGroupStoreAssoc.findByIStoreID", query = "SELECT o FROM OwnerGroupStoreAssoc o WHERE o.iStoreID = :iStoreID")})
public class OwnerGroupStoreAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "iOwnerGroupID")
    private Integer iOwnerGroupID;
    @Column(name = "iStoreID")
    private Integer iStoreID;

    public OwnerGroupStoreAssoc() {
    }

    public OwnerGroupStoreAssoc(Integer iID) {
        this.iID = iID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public Integer getIOwnerGroupID() {
        return iOwnerGroupID;
    }

    public void setIOwnerGroupID(Integer iOwnerGroupID) {
        this.iOwnerGroupID = iOwnerGroupID;
    }

    public Integer getIStoreID() {
        return iStoreID;
    }

    public void setIStoreID(Integer iStoreID) {
        this.iStoreID = iStoreID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iID != null ? iID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OwnerGroupStoreAssoc)) {
            return false;
        }
        OwnerGroupStoreAssoc other = (OwnerGroupStoreAssoc) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.api.domain.self.store.OwnerGroupStoreAssoc[ iID=" + iID + " ]";
    }
    
}
