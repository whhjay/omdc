package com.angus.omdc.message;

/**
 * Security Status (21)
 * 
 * The Security Status message is generated
 *  At the start of the business day if the security is not available for trading.
 *  Whenever a security state changes.
 * 
 * Total Length ....................................................................... 12
 * 
 * @author wangjia
 *
 */
public class SecurityStatusMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	/**
	 * 2 Trading Halt or Suspend
	 * 3 Resume
	 */
	short suspensionIndicator; // uint8
	byte[] filler3 = new byte[3]; // byte3
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public short getSuspensionIndicator() {
		return suspensionIndicator;
	}
	public void setSuspensionIndicator(short suspensionIndicator) {
		this.suspensionIndicator = suspensionIndicator;
	}
	public byte[] getFiller3() {
		return filler3;
	}
	public void setFiller3(byte[] filler3) {
		this.filler3 = filler3;
	}
}
