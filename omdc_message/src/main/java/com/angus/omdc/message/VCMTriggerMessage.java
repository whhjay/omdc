package com.angus.omdc.message;

/**
 * VCM Trigger (23)
 * 
 * The VCM Trigger message is generated whenever a cooling off period is triggered by Volatility Control Mechanism (VCM).
 * 
 * Total Length ....................................................................... 36
 * 
 * @author wangjia
 *
 */
public class VCMTriggerMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	long collingOffStartTime; // uint64, nanoseconds
	long collingOffEndTime; // uint64, nanoseconds
	int vcmREferencePrice; // int32, 3 implied decimal places
	int vcmLowerPrice; // int32, 3 implied decimal places
	int vcmUpperPrice; // int32, 3 implied decimal places
	//
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public long getCollingOffStartTime() {
		return collingOffStartTime;
	}
	public void setCollingOffStartTime(long collingOffStartTime) {
		this.collingOffStartTime = collingOffStartTime;
	}
	public long getCollingOffEndTime() {
		return collingOffEndTime;
	}
	public void setCollingOffEndTime(long collingOffEndTime) {
		this.collingOffEndTime = collingOffEndTime;
	}
	public int getVcmREferencePrice() {
		return vcmREferencePrice;
	}
	public void setVcmREferencePrice(int vcmREferencePrice) {
		this.vcmREferencePrice = vcmREferencePrice;
	}
	public int getVcmLowerPrice() {
		return vcmLowerPrice;
	}
	public void setVcmLowerPrice(int vcmLowerPrice) {
		this.vcmLowerPrice = vcmLowerPrice;
	}
	public int getVcmUpperPrice() {
		return vcmUpperPrice;
	}
	public void setVcmUpperPrice(int vcmUpperPrice) {
		this.vcmUpperPrice = vcmUpperPrice;
	}
}
