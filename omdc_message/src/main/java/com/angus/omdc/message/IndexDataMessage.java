package com.angus.omdc.message;

/**
 * Index Data (71)
 * 
 * The Index Data message contains all the real-time data for a given index and real-time market information. Fields within this
 * message may be populated with null values to identify when an update is not provided. See section 3.1.1 (Null Values) for
 * more information about null values.
 * Note: For IndexCode “CSCSHQ” which is for Shanghai-Hong Kong Stock Connect Northbound Daily Quota Balance, and
 * IndexCode “CSCSZQ” which is for Shenzhen-Hong Kong Stock Connect Northbound Daily Quota Balance,only
 * MsgSize, MsgType, IndexCode, IndexTime and IndexVolume will be populated to provide the daily quota balance
 * whereas the other fields are irrelevant and can be ignored.
 * 
 * Total Length....................................................................... 112
 * 
 * @author wangjia
 *
 */
public class IndexDataMessage extends OmdMessage {
	String indexCode; // string11
	/**
	 * C Closing value
	 * I Indicative
	 * O Opening index
	 * P Last close value (prev. ses.)
	 * R Preliminary close
	 * S Stop loss index
	 * T Real-time index value
	 * IndexStatus can be blank if not
	 * defined by third party index compilers
	 */
	String indexStatus; // string1
	long indexTime; // int64, nanoseconds
	long indexValue; // int64, 4 implied decimal places
	long netChgPrevDay; // int64, 4 implied decimal places
	long highValue; // int64, 4 implied decimal places
	long lowValue; // int64, 4 implied decimal places
	long easValue; // int64, 2 implied decimal places
	long indexTurnover; // int64, 4 implied decimal places
	long openingValue; // int64, 4 implied decimal places
	long closingValue; // int64, 4 implied decimal places
	long previousSesClose; // int64, 4 implied decimal places
	long indexVolume; // int64
	int netChgPrevDayPct; // int32, 4 implied decimal places
	/**
	 * # Index with HSIL defined
	 *   exceptional rule applied
	 * ' ' Normal index (empty string)
	 */
	String exception; // string1, 
	byte[] filler3 = new byte[3];
	
	
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getIndexStatus() {
		return indexStatus;
	}
	public void setIndexStatus(String indexStatus) {
		this.indexStatus = indexStatus;
	}
	public long getIndexTime() {
		return indexTime;
	}
	public void setIndexTime(long indexTime) {
		this.indexTime = indexTime;
	}
	public long getIndexValue() {
		return indexValue;
	}
	public void setIndexValue(long indexValue) {
		this.indexValue = indexValue;
	}
	public long getNetChgPrevDay() {
		return netChgPrevDay;
	}
	public void setNetChgPrevDay(long netChgPrevDay) {
		this.netChgPrevDay = netChgPrevDay;
	}
	public long getHighValue() {
		return highValue;
	}
	public void setHighValue(long highValue) {
		this.highValue = highValue;
	}
	public long getLowValue() {
		return lowValue;
	}
	public void setLowValue(long lowValue) {
		this.lowValue = lowValue;
	}
	public long getEasValue() {
		return easValue;
	}
	public void setEasValue(long easValue) {
		this.easValue = easValue;
	}
	public long getIndexTurnover() {
		return indexTurnover;
	}
	public void setIndexTurnover(long indexTurnover) {
		this.indexTurnover = indexTurnover;
	}
	public long getOpeningValue() {
		return openingValue;
	}
	public void setOpeningValue(long openingValue) {
		this.openingValue = openingValue;
	}
	public long getClosingValue() {
		return closingValue;
	}
	public void setClosingValue(long closingValue) {
		this.closingValue = closingValue;
	}
	public long getPreviousSesClose() {
		return previousSesClose;
	}
	public void setPreviousSesClose(long previousSesClose) {
		this.previousSesClose = previousSesClose;
	}
	public long getIndexVolume() {
		return indexVolume;
	}
	public void setIndexVolume(long indexVolume) {
		this.indexVolume = indexVolume;
	}
	public int getNetChgPrevDayPct() {
		return netChgPrevDayPct;
	}
	public void setNetChgPrevDayPct(int netChgPrevDayPct) {
		this.netChgPrevDayPct = netChgPrevDayPct;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public byte[] getFiller3() {
		return filler3;
	}
	public void setFiller3(byte[] filler3) {
		this.filler3 = filler3;
	}
}
