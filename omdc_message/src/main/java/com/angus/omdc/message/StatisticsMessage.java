package com.angus.omdc.message;

/**
 * Statistics (60)
 * 
 * The Statistics message provides statistics including volume-weighted average price and turnover. It is generated after every
 * trade or trade cancel excluding overseas trades.
 * Note that the ‘VWAP’ field is not populated for SS (OMD Securities Standard) clients.
 * The ShortSellSharesTraded and ShortSellTurnover fields (the shortsell fields) are only updated twice each day at most for
 * securities with shortselling activities - at the end of the morning session if the shortsell fields are non-zero and at the end of
 * the afternoon session if the value of any of the shortsell fields are different from that disseminated at the end of the morning
 * session.
 * 
 * Total Length....................................................................... 52
 * 
 * @author wangjia
 *
 */
public class StatisticsMessage extends OmdMessage {
	int securityCode;	// uint32, 1-99999
	long sharesTraded;	// uint64
	long turnover;		// int64, 3 implied decimal places
	int highPrice;		// int32, 3 implied decimal places
	int lowPrice;		// int32, 3 implied decimal places
	int lastPrice;		// int32, 3 implied decimal places
	/**
	 * Volume-Weighted Average Price.
	 */
	int vwap;			// int32, 3 implied decimal places
	long shortSellSharesTraded;	// uint32
	long shortSellTurnover;	// int64
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public long getSharesTraded() {
		return sharesTraded;
	}
	public void setSharesTraded(long sharesTraded) {
		this.sharesTraded = sharesTraded;
	}
	public long getTurnover() {
		return turnover;
	}
	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}
	public int getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(int highPrice) {
		this.highPrice = highPrice;
	}
	public int getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}
	public int getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(int lastPrice) {
		this.lastPrice = lastPrice;
	}
	public int getVwap() {
		return vwap;
	}
	public void setVwap(int vwap) {
		this.vwap = vwap;
	}
	public long getShortSellSharesTraded() {
		return shortSellSharesTraded;
	}
	public void setShortSellSharesTraded(long shortSellSharesTraded) {
		this.shortSellSharesTraded = shortSellSharesTraded;
	}
	public long getShortSellTurnover() {
		return shortSellTurnover;
	}
	public void setShortSellTurnover(long shortSellTurnover) {
		this.shortSellTurnover = shortSellTurnover;
	}
}
