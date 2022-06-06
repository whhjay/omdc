package com.angus.omdc.message;

/**
 * Currency Rate (14)
 * 
 * The Currency Rate message provides the foreign exchange conversion rates between various foreign currencies and the
 * Hong Kong dollar.
 * The Currency Factor and Currency Rate fields should be interpreted as below:
 * For example if 1 Euro is valued 10.22 HKD
 *  Currency Factor will be 0 (1 EUR)
 *  Currency Rate will be 102200 (4 decimals implied)
 * For example if 1000 Japanese Yen is worth 90.678 HKD
 *  Currency Factor will be 3 (1000 JPY)
 *  Currency Rate will be 906780 (4 decimals implied)
 * 
 * @author wangjia
 *
 */
public class CurrencyRateMessage extends OmdMessage {
	String currencyCode; // string3
	byte filler1;
	int currencyFactor; // uint16
	byte[] filler2 = new byte[2];
	long currencyRate; // uint32
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public byte getFiller1() {
		return filler1;
	}
	public void setFiller1(byte filler1) {
		this.filler1 = filler1;
	}
	public int getCurrencyFactor() {
		return currencyFactor;
	}
	public void setCurrencyFactor(int currencyFactor) {
		this.currencyFactor = currencyFactor;
	}
	public byte[] getFiller2() {
		return filler2;
	}
	public void setFiller2(byte[] filler2) {
		this.filler2 = filler2;
	}
	public long getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(long currencyRate) {
		this.currencyRate = currencyRate;
	}
}
