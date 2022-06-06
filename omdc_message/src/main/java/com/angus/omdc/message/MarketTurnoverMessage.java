package com.angus.omdc.message;

/**
 * Market Turnover (61)
 * 
 * The Market Turnover message contains the total turnover (excluding the turnover of overseas trades) for all securities on a
 * given market segment for a given trading currency. It also provides the total turnover (excluding the turnover of overseas
 * trades) for all securities regardless of trading currency on a given market segment in HKD equivalent. Under normal
 * circumstances, the updates are disseminated around every 2 seconds during the trading hours.
 * When the CurrencyCode is blank, the turnover represents the total turnover traded on the given market segment for all
 * trading currencies, expressed in HKD.
 * 
 * @author wangjia
 *
 */
public class MarketTurnoverMessage extends OmdMessage {
	String marketCode; // string4
	String currencyCode; // string3
	byte filler; // byte1
	long turnover; // int64, total traded turnover, 3 implied decimal places.
	
	public byte getFiller() {
		return filler;
	}
	public void setFiller(byte filler) {
		this.filler = filler;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public long getTurnover() {
		return turnover;
	}
	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}
}
