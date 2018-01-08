package com.unityhealth.api.retrieveUserInfo.connect;

/**
 *
 * @author Oliver
 */

public class User {

    protected String login;
    protected String name;
    protected String email;
    protected String principalID;

    /**
     * Instantiate the Course with the name, launch URL
     *
     * @param name Users full name
     * @param principalID Adobe Connect/LMS UserID for this user
     * @param login User login
     * @param email User email
     */
    public User(String principalID, String login, String name, String email) {

        this.principalID = principalID;
        this.login = login;
        this.name = name;
        this.email = email;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return
     */
    public String getPrincipalID() {
        return this.principalID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [login=" + login + ", name=" + name + ", email=" + email
                + ", principalID=" + principalID + "]";
    }
}
