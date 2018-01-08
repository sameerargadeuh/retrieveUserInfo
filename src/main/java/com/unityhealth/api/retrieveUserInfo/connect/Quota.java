package com.unityhealth.api.retrieveUserInfo.connect;

public class Quota {

    int used;
    int limit;

    /**
     * @param used
     * @param limit
     */
    public Quota(int used, int limit) {
        super();
        this.used = used;
        this.limit = limit;
    }

    /**
     * @return the used
     */
    public int getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(int used) {
        this.used = used;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Quota [used=" + used + ", limit=" + limit + "]";
    }
}
