package com.unityhealth.api.retrieveUserInfo.connect;

public class ResponseStatus {

    boolean enrolled = false;
    boolean quotaReached = false;

    /**
     *
     */
    public ResponseStatus() {
    }

    /**
     * @param enrolled
     * @param quotaReached
     */
    public ResponseStatus(boolean enrolled, boolean quotaReached) {
        super();
        this.enrolled = enrolled;
        this.quotaReached = quotaReached;
    }

    /**
     * @return the enrolled
     */
    public boolean isEnrolled() {
        return enrolled;
    }

    /**
     * @param enrolled the enrolled to set
     */
    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    /**
     * @return the quotaReached
     */
    public boolean isQuotaReached() {
        return quotaReached;
    }

    /**
     * @param quotaReached the quotaReached to set
     */
    public void setQuotaReached(boolean quotaReached) {
        this.quotaReached = quotaReached;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseStatus [enrolled=" + enrolled + ", quotaReached="
                + quotaReached + "]";
    }
}
