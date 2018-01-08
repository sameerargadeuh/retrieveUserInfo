package com.unityhealth.api.retrieveUserInfo.connect;

import java.text.ParseException;

/**
 * This class is an encapsulation of the Course data.  Using this class
 * allows us to hide XML details from the JSP pages that need this information.
 *
 */
public class Course {

    protected String name;
    protected String scoID;
    protected String launchURL;
    protected String desc;
    protected String closeDate;
    protected String breezesession;

    public Course(String name, String scoID, String launchURL, String desc, String closeDate) throws
            ParseException {

        this.name = name;
        this.scoID = scoID;
        this.launchURL = launchURL;
        this.desc = desc;
        this.closeDate = closeDate;
    }

    public void setBreezesession(String breezesession) {
        this.breezesession = breezesession;
        this.launchURL = this.launchURL + "?session=" + breezesession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setScoID(String scoID) {
        this.scoID = scoID;
    }

    public String getScoID() {
        return this.scoID;
    }

    public void setLaunchURL(String launchURL) {
        this.launchURL = launchURL;
    }

    public String getLaunchURL() {
        return this.launchURL;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getCloseDate() {
        return this.closeDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
            return "Course [name=" + name + ", scoID=" + scoID + ", launchURL="
                            + launchURL + ", desc=" + desc + ", closeDate=" + closeDate
                            + ", breezesession=" + breezesession + "]";
    }
}