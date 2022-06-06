package com.angus.omdc.message;

/**
 * Market Definition (10)
 * The Market Definition message is generated at the start of the business day for each market segment.
 * @author wangjia
 *
 */
public class MarketDefinitionMessage extends OmdMessage {
	String marketCode; // string4, MAIN/GEM/NASD/ETS
	String marketName; // string25
	String currencyCode; // string3
	long numberOfSecurities; // uint32
	
	
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public long getNumberOfSecurities() {
		return numberOfSecurities;
	}
	public void setNumberOfSecurities(long numberOfSecurities) {
		this.numberOfSecurities = numberOfSecurities;
	}
}
