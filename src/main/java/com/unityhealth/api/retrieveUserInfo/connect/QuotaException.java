package com.unityhealth.api.retrieveUserInfo.connect;

/**
 * @author Franco
 *
 */
public class QuotaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6530486444545963162L;

	/**
		 * 
		 */
	public QuotaException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public QuotaException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public QuotaException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public QuotaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
