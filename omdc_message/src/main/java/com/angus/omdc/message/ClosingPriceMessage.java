package com.angus.omdc.message;

/**
 * Closing Price (62)
 * 
 * The Closing Price message is generated near the end of the business day for each security. If the closing price is not
 * available, the field ‘ClosingPrice’ is set to 0. Note that the ‘NumberOfTrades’ field is not populated for SS (OMD Securities
 * Standard) clients.
 * 
 * @author wangjia
 *
 */
public class ClosingPriceMessage extends OmdMessage {
	int securityCode;	// uint32, 1-99999
	int closingPrice;	// int32, 3 implied decimal places
	long numberOfTrades; // uint32, Total Number of Trades performed on the given instrument
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(int closingPrice) {
		this.closingPrice = closingPrice;
	}
	public long getNumberOfTrades() {
		return numberOfTrades;
	}
	public void setNumberOfTrades(long numberOfTrades) {
		this.numberOfTrades = numberOfTrades;
	}
}
