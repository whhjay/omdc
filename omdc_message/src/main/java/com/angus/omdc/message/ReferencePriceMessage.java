package com.angus.omdc.message;

/**
 * Reference Price (43)
 * 
 * This message provides the reference price, lower and upper price limits for order input during an applicable auction session
 * and will be sent again when there is any change of the reference price, lower and upper price limits during the session. Also
 * the same information may be resent during the auction session.
 * For CAS (Closing Auction Session), a Reference Price message is generated at the start of the session for all the securities
 * tradable on the day, regardless of whether it is a CAS applicable security or not.
 * For (POS) Pre-Opening Session, no Reference Price messages are sent.
 * 
 * Total Length ....................................................................... 20
 * 
 * @author wangjia
 *
 */
public class ReferencePriceMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	int referencePrice; // int32, 3 implied decimal places
	int lowerPrice; // int32, 3 implied decimal places, 0 means N/A
	int upperPrice; // int32, 3 implied decimal places, 0 means N/A
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(int referencePrice) {
		this.referencePrice = referencePrice;
	}
	public int getLowerPrice() {
		return lowerPrice;
	}
	public void setLowerPrice(int lowerPrice) {
		this.lowerPrice = lowerPrice;
	}
	public int getUpperPrice() {
		return upperPrice;
	}
	public void setUpperPrice(int upperPrice) {
		this.upperPrice = upperPrice;
	}
}
