package com.angus.omdc.message;


import com.angus.omdc.annotation.DynamicLengthMessage;

import java.util.List;

/**
 * News (22)
 * 
 * The News message is generated whenever a news update occurs. The message indicates which markets and/or securities
 * the news is applied to. If NoMarketCode and NoSecurityCodes are both set to zero, the news applies to all markets.
 * The news may be fragmented across multiple consecutive messages. The LastFragment field will be set to ‘Y’ in the
 * message that contains the last fragment. The "Headline" will only be carried in the first message and blanked from the
 * second message onwards.
 * 
 * @author wangjia
 *
 */
@DynamicLengthMessage("356 + 4nM + 4nS+160np "
		+ "(nM = value of NoMarketCodes)"
		+ "(nS = value of NoSecurityCodes)"
		+ "(np = value of NoNewsLines)"
		)
public class NewsMessage extends OmdMessage {
	String newsType; // string3, EXN: Exchange news, EXC: Chinese Exchange news
	String newsId; // string3
	String headline; // string320, refer newsType , EXN - ascii, EXC - UTF-16LE
	String cancelFlag; // string1, Y: cancelled, N: not cancelled
	String lastFragment; // string1, Y: complete, N: not complete
	byte filler4[] = new byte[4]; // byte4
	long releaseTime; // uint64, nanoseconds
	byte filler2[] = new byte[2]; // byte2
	
	int noMarketCodes; // uint16, 0 to 4
	List<String> marketCode; // string4, MAIN/GEM/NASD/ETS
	
	byte filler2b[] = new byte[2];
	int noSecurityCodes; // uint16, 0 to 200
	List<Integer> securityCode; // uint32, 1-99999
	
	byte filler2c[] = new byte[2];
	int noNewsLines; // uint16, <= 10
	List<String> newsLine; // string160, refer newsType , EXN - ascii, EXC - UTF-16LE
	//
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getLastFragment() {
		return lastFragment;
	}
	public void setLastFragment(String lastFragment) {
		this.lastFragment = lastFragment;
	}
	public byte[] getFiller4() {
		return filler4;
	}
	public void setFiller4(byte[] filler4) {
		this.filler4 = filler4;
	}
	public long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
	public byte[] getFiller2() {
		return filler2;
	}
	public void setFiller2(byte[] filler2) {
		this.filler2 = filler2;
	}
	public int getNoMarketCodes() {
		return noMarketCodes;
	}
	public void setNoMarketCodes(int noMarketCodes) {
		this.noMarketCodes = noMarketCodes;
	}
	public List<String> getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(List<String> marketCode) {
		this.marketCode = marketCode;
	}
	public byte[] getFiller2b() {
		return filler2b;
	}
	public void setFiller2b(byte[] filler2b) {
		this.filler2b = filler2b;
	}
	public int getNoSecurityCodes() {
		return noSecurityCodes;
	}
	public void setNoSecurityCodes(int noSecurityCodes) {
		this.noSecurityCodes = noSecurityCodes;
	}
	public List<Integer> getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(List<Integer> securityCode) {
		this.securityCode = securityCode;
	}
	public byte[] getFiller2c() {
		return filler2c;
	}
	public void setFiller2c(byte[] filler2c) {
		this.filler2c = filler2c;
	}
	public int getNoNewsLines() {
		return noNewsLines;
	}
	public void setNoNewsLines(int noNewsLines) {
		this.noNewsLines = noNewsLines;
	}
	public List<String> getNewsLine() {
		return newsLine;
	}
	public void setNewsLine(List<String> newsLine) {
		this.newsLine = newsLine;
	}
}
