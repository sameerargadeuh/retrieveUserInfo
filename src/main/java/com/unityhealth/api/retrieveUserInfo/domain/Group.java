package com.unityhealth.api.retrieveUserInfo.domain;




import javax.persistence.*;
import java.util.Set;

/**
 * Created by shanefox on 24/10/2016.
 */
@Entity
@Table(name = "tblgroups")
public class Group {

    /**
     * @return the vParentCompany
     */
    public String getvParentCompany() {
        return vParentCompany;
    }

    /**
     * @param vParentCompany the vParentCompany to set
     */
    public void setvParentCompany(String vParentCompany) {
        this.vParentCompany = vParentCompany;
    }

   

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "iID")
    private Integer id;
    @Column(name = "vName")
    private String vName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade=CascadeType.ALL)
    private Set<Store> stores;

   @Column(name = "bDisplayFirst")
    private Integer   bDisplayFirst;
   
   @Column(name = "vType")
   private String vType;
   @Column(name = "vParentCompany")
   private String vParentCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
     /**
     * @return the vName
     */
    public String getvName() {
        return vName;
    }

    /**
     * @param vName the vName to set
     */
    public void setvName(String vName) {
        this.vName = vName;
    }

    /**
     * @return the bDisplayFirst
     */
    public Integer getbDisplayFirst() {
        return bDisplayFirst;
    }

    /**
     * @param bDisplayFirst the bDisplayFirst to set
     */
    public void setbDisplayFirst(Integer bDisplayFirst) {
        this.bDisplayFirst = bDisplayFirst;
    }
    
    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }
    
}
