package com.angus.omdc.message;

/**
 * Yield (44)
 * 
 * The Yield message is generated for bond securities when their yield percentage changes.
 * 
 * Total Length ....................................................................... 12
 * 
 * @author wangjia
 *
 */
public class YieldMessage extends OmdMessage {
	int securityCode;	// uint32, 1-99999
	int yield; // int32, 3 implied decimal places, 0 means N/A
	//
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getYield() {
		return yield;
	}
	public void setYield(int yield) {
		this.yield = yield;
	}
}
