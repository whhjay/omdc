package com.angus.omdc.message;

/**
 * Trade Ticker (52)
 * 
 * The Trade Ticker is an aggregation of several trades into one message, combining quantities of subsequent trades made on
 * a given instrument at a given fixed price.
 * When a trade is cancelled, a Trade Ticker message will be generated with the TickerID set to the ticker which contains the
 * cancelled trade and with the AggregateQuantity set to remaining quantity outstanding.
 *  
 *  Total Length....................................................................... 36
 *  
 * @author wangjia
 *
 */
public class TradeTickerMessage extends OmdMessage {
	int securityCode;	// uint32, 1-99999
	long tickerId;		// uint32
	int price;			// int32, 3 implied decimal places
	/**
	 * Remaining quantity if TrdCancelFlag = Y
	 */
	long aggregateQuantity;	// uint64, 
	long tradeTime;		// uint64, nanoseconds
	/**
	 * 0 Automatch normal (AMS<space>)
	 * 4 Late Trade (Off-exchangeprevious day) (AMS “P”)
	 * 22 Non-direct Off-Exchange Trade(AMS “M”)
	 * 100 Automatch internalized (AMS“Y”)
	 * 101 Direct off-exchange Trade(AMS “X”)
	 * 102 Odd-Lot Trade (AMS “D”)
	 * 103 Auction Trade (AMS “U”)
	 * Not applicable when TrdCancelFlag= Y
	 */
	short trdType;		// int16
	/**
	 * Y Cancelled
	 * N Not cancelled
	 */
	String trdCancelFlag; // string1
	byte filler; // byte1
	//
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public long getTickerId() {
		return tickerId;
	}
	public void setTickerId(long tickerId) {
		this.tickerId = tickerId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getAggregateQuantity() {
		return aggregateQuantity;
	}
	public void setAggregateQuantity(long aggregateQuantity) {
		this.aggregateQuantity = aggregateQuantity;
	}
	public long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
	}
	public short getTrdType() {
		return trdType;
	}
	public void setTrdType(short trdType) {
		this.trdType = trdType;
	}
	public String getTrdCancelFlag() {
		return trdCancelFlag;
	}
	public void setTrdCancelFlag(String trdCancelFlag) {
		this.trdCancelFlag = trdCancelFlag;
	}
	public byte getFiller() {
		return filler;
	}
	public void setFiller(byte filler) {
		this.filler = filler;
	}
}
