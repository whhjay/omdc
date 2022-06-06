package com.angus.omdc.message;

/**
 * Index Definition (70)
 * 
 * The Index Definition message contains the static referential data for the given index and market information and is
 * generated at the start of the business day and may be re-disseminated during the trading hours.
 * 
 * Total Length....................................................................... 20
 * 
 * @author wangjia
 *
 */
public class IndexDefinitionMessage extends OmdMessage {
	String indexCode; // string11
	String indexSource; // string1
	String currencyCode; // string3
	byte filler; // byte
	
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getIndexSource() {
		return indexSource;
	}
	public void setIndexSource(String indexSource) {
		this.indexSource = indexSource;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public byte getFiller() {
		return filler;
	}
	public void setFiller(byte filler) {
		this.filler = filler;
	}
}
