package com.angus.omdc.message;

/**
 * Trading Session Status (20)
 * 
 * The Trading Session Status provides information on the status of a market segment. It is sent whenever there is change of
 * trading session.
 * This message may be sent on a separate multicast channel from order and trade data and therefore may not be
 * synchronized.
 * 
 * Total Length ....................................................................... 32
 * 
 * @author wangjia
 *
 */
public class TradingSessionStatusMessage extends OmdMessage {
	String marketCode;	// string4, MAIN/GEM/NASD/ETS
	/**
	 * 1  Day
	 */
	short tradingSessionId;	// uint8
	/**
	 * 0 Day Close (DC)
	 * 1 Pre-trading [Pre-Opening
	 *   Session (POS) Order Input
	 *   (OI)
	 * 2 Opening or Opening Auction
	 *   [POS] Matching (MA)
	 * 3 Continuous trading (CT)
	 * 4 Closing or Closing Auction
	 *   [Closing Auction Session
	 *   (CAS)] Matching (MA)
	 * 5 Post-trading [CAS] Order Input
	 *   (OI)
	 * 7 Quiescent (i.e. Blocking) (BL)
	 * 100 Not Yet Open (NO)
	 * 101 No Cancel/Modification [POS]
	 *   (NC)
	 * 102 Exchange Intervention (EI)
	 * 103 Close (CL)
	 * 104 Order Cancel (OC)
	 * 105 Reference Price Fixing [CAS]
	 *   (RP)
	 * 106 No Cancellation [CAS] (NW)
	 * 107 Random Close [CAS] (RC)
	 */
	short tradingSessionSubId; // uint8
	/**
	 * 0 Unknown (for NO)
	 * 1 Halted (for BL, EI)
	 * 2 Open (for [POS] OI, [POS] NC,
	 *   [POS] MA, CT, OC)
	 * 3 Closed (for CL)
	 * 5 Pre-Close (for [CAS] RP,
	 *   [CAS] NW, [CAS] RC, [CAS]
	 *   MA, [CAS] OI)
	 * 100 Day Closed (for DC)
	 */
	short tradingSesStatus;	// uint8
	/**
	 * ‘0’ Automatic (Default)
	 * ‘1’ Manual (this invalidates the
	 *   normal schedule for the day)
	 */
	String tradingSesControlFlag; // string1, 
	byte[] filler4 = new byte[4]; // byte4
	long startDateTime; // uint64, nanoseconds
	long endDateTime; // uint64, nanoseconds
	//
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public short getTradingSessionId() {
		return tradingSessionId;
	}
	public void setTradingSessionId(short tradingSessionId) {
		this.tradingSessionId = tradingSessionId;
	}
	public short getTradingSessionSubId() {
		return tradingSessionSubId;
	}
	public void setTradingSessionSubId(short tradingSessionSubId) {
		this.tradingSessionSubId = tradingSessionSubId;
	}
	public short getTradingSesStatus() {
		return tradingSesStatus;
	}
	public void setTradingSesStatus(short tradingSesStatus) {
		this.tradingSesStatus = tradingSesStatus;
	}
	public String getTradingSesControlFlag() {
		return tradingSesControlFlag;
	}
	public void setTradingSesControlFlag(String tradingSesControlFlag) {
		this.tradingSesControlFlag = tradingSesControlFlag;
	}
	public byte[] getFiller4() {
		return filler4;
	}
	public void setFiller4(byte[] filler4) {
		this.filler4 = filler4;
	}
	public long getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(long startDateTime) {
		this.startDateTime = startDateTime;
	}
	public long getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(long endDateTime) {
		this.endDateTime = endDateTime;
	}
}
