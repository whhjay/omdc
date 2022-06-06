package com.angus.omdc.message;


import com.angus.omdc.annotation.DynamicLengthMessage;

import java.util.List;

/**
 * Security Definition (11)
 * <p>
 * This Security Definition message contains all the reference data for a security.
 * Security Definition messages may be received intraday – for example the ‘FreeText’ field may be updated during the day.
 * Please note the following shows two different message layouts which the first one is current production whereas the other
 * one will come into effect from the launch of Closing Auction Session (CAS).
 *
 * @author wangjia
 */
@DynamicLengthMessage("464 + 8nU (nU = value of NoUnderlyingSecurities)")
public class SecurityDefinitionMessage extends OmdMessage {
    /**
     * @author:WangJia
     */
    private static final long serialVersionUID = -1127890681916029398L;
    int securityCode; // uint32, 1-99999
    String marketCode; // string4, MAIN/GEM/NASD/ETS
    String isinCode; // string12
    String instrumentType;    // string4, BOND/BWRT/EQTY/TRST/WRNT
    byte[] filler2 = new byte[2]; // byte2
    //
    /**
     * Spread table as per Second
     * Schedule of Rules of the Exchange:
     * ‘01’ Part A
     * ‘03’ Part B
     */
    String spreadTableCode;        // string2,
    String securityShortName;    // string40
    String currencyCode;        // string3
    String securityNameGCCS;    // binary60, UTF-16LE
    String securityNameGB;        // binary60, UTF-16LE
    long lotSize;    // uint32
    byte[] filler4 = new byte[4]; // byte4
    //
    int previousClosingPrice;    // int32, 3 implied decimal places
    /**
     * Y VCM applicable
     * N VCM not applicable
     */
    String vcmFlag;    // string1
    /**
     * Y Short-sell allowed
     * N Short-sell not allowed
     */
    String shortSellFlag;    // string1
    /**
     * Y CAS applicable
     * N CAS not applicable
     */
    String casFlag;    // string1
    /**
     * Y CCASS security
     * N Non CCASS security
     */
    String ccassFlag;    // string1
    /**
     * Y Dummy security
     * N Normal security
     */
    String dummySecurityFlag;    // string1
    /**
     * Y Test security
     * N Normal security
     */
    String testSecurityFlag;    // string1
    /**
     * Y Stamp duty required
     * N Stamp duty not required
     */
    String stampDutyFlag;        // string1
    byte filler1;    // byte
    //
    int listingDate;    // uint32, YYYYMMDD, 19000101 for unknown
    int delistingDate;    // uint32, YYYYMMDD, 0 if no date exists
    String freeText;    // string38
    byte[] filler82 = new byte[82]; // byte82
    //
    /**
     * Y EFN
     * N Non-EFN
     */
    String efnFlag;        // string1
    long accruedInterest;    // uint32, 3 implied decimal places
    long couponRate;    // uint32, 3 implied decimal places
    byte[] filler42 = new byte[42]; // byte42
    //
    long conversionRatio;    // uint32, 3 implied decimal places
    int strikePrice;    // int32, 3 implied decimal places

    /**
     * Upper strike price of the
     * security if it has lower and
     * upper strike prices
     * 3 implied decimal places
     * Value is 0 if the securities has only
     * one strike price
     */
    int strikePrice2;    // int32, 3 implied decimal places
    int maturityDate;    // YYYYMMDD
    /**
     * For Derivative Warrants/Basket
     * Warrants:
     * C Call
     * P Put
     * O Others
     * For ELI & CBBC:
     * C Bull
     * P Bear / Rang
     */
    String callPutFlag; // string1
    /**
     * A American style
     * E European style
     * <blank> Other
     */
    String style;    // string1
    /**
     * N Normal instrument
     * X Exotic instrument
     * “0” Not available
     */
    String warrantType; //String1

    /**
     * Call price for CBBC
     * See DecimalsInCallPrice for the
     * number of decimal places defined
     * 0 Not available
     */
    int callPrice;  // Int32
    /**
     * Number of decimal places in Call Price
     * Not applicable if CallPrice = 0
     */
    short decimalsInCallPrice;  //Uint8
    /**
     * Entitlement of the warrant
     * See DecimalsInEntitlement for the
     * number of decimal places defined
     * 0 Not available
     */
    int entitlement; //Int32
    /**
     * Number of decimal places in Entitlement
     * Not applicable if Entitlement = 0
     */
    short decimalsInEntitlement; //Uint8
    /**
     * Number of warrants per entitlement
     * Not applicable if Entitlement = 0
     */
    long noWarrantsPerEntitlement; //Uint32
    byte[] filler33 = new byte[33]; // byte33
    /**
     * 0 to 20 for Basket Warrants
     * 0 to 1 for Warrants and Structured
     * Product
     */
    int noUnderlyingSecurities; // uint16

    List<Entry> underlyingSecurities;

    public static class Entry {
        int underlyingSecurityCode; // uint32, 1-99999
        long underlyingSecurityWeight; // uint32, 3 implied decimal places

        public int getUnderlyingSecurityCode() {
            return underlyingSecurityCode;
        }

        public void setUnderlyingSecurityCode(int underlyingSecurityCode) {
            this.underlyingSecurityCode = underlyingSecurityCode;
        }

        public long getUnderlyingSecurityWeight() {
            return underlyingSecurityWeight;
        }

        public void setUnderlyingSecurityWeight(long underlyingSecurityWeight) {
            this.underlyingSecurityWeight = underlyingSecurityWeight;
        }
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public byte[] getFiller2() {
        return filler2;
    }

    public void setFiller2(byte[] filler2) {
        this.filler2 = filler2;
    }

    public String getSpreadTableCode() {
        return spreadTableCode;
    }

    public void setSpreadTableCode(String spreadTableCode) {
        this.spreadTableCode = spreadTableCode;
    }

    public String getSecurityShortName() {
        return securityShortName;
    }

    public void setSecurityShortName(String securityShortName) {
        this.securityShortName = securityShortName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getSecurityNameGCCS() {
        return securityNameGCCS;
    }

    public void setSecurityNameGCCS(String securityNameGCCS) {
        this.securityNameGCCS = securityNameGCCS;
    }

    public String getSecurityNameGB() {
        return securityNameGB;
    }

    public void setSecurityNameGB(String securityNameGB) {
        this.securityNameGB = securityNameGB;
    }

    public long getLotSize() {
        return lotSize;
    }

    public void setLotSize(long lotSize) {
        this.lotSize = lotSize;
    }

    public byte[] getFiller4() {
        return filler4;
    }

    public void setFiller4(byte[] filler4) {
        this.filler4 = filler4;
    }

    public int getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public void setPreviousClosingPrice(int previousClosingPrice) {
        this.previousClosingPrice = previousClosingPrice;
    }

    public String getVcmFlag() {
        return vcmFlag;
    }

    public void setVcmFlag(String vcmFlag) {
        this.vcmFlag = vcmFlag;
    }

    public String getShortSellFlag() {
        return shortSellFlag;
    }

    public void setShortSellFlag(String shortSellFlag) {
        this.shortSellFlag = shortSellFlag;
    }

    public String getCasFlag() {
        return casFlag;
    }

    public void setCasFlag(String casFlag) {
        this.casFlag = casFlag;
    }

    public String getCcassFlag() {
        return ccassFlag;
    }

    public void setCcassFlag(String ccassFlag) {
        this.ccassFlag = ccassFlag;
    }

    public String getDummySecurityFlag() {
        return dummySecurityFlag;
    }

    public void setDummySecurityFlag(String dummySecurityFlag) {
        this.dummySecurityFlag = dummySecurityFlag;
    }

    public String getTestSecurityFlag() {
        return testSecurityFlag;
    }

    public void setTestSecurityFlag(String testSecurityFlag) {
        this.testSecurityFlag = testSecurityFlag;
    }

    public String getStampDutyFlag() {
        return stampDutyFlag;
    }

    public void setStampDutyFlag(String stampDutyFlag) {
        this.stampDutyFlag = stampDutyFlag;
    }

    public byte getFiller1() {
        return filler1;
    }

    public void setFiller1(byte filler1) {
        this.filler1 = filler1;
    }

    public int getListingDate() {
        return listingDate;
    }

    public void setListingDate(int listingDate) {
        this.listingDate = listingDate;
    }

    public int getDelistingDate() {
        return delistingDate;
    }

    public void setDelistingDate(int delistingDate) {
        this.delistingDate = delistingDate;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public byte[] getFiller82() {
        return filler82;
    }

    public void setFiller82(byte[] filler82) {
        this.filler82 = filler82;
    }

    public String getEfnFlag() {
        return efnFlag;
    }

    public void setEfnFlag(String efnFlag) {
        this.efnFlag = efnFlag;
    }

    public long getAccruedInterest() {
        return accruedInterest;
    }

    public void setAccruedInterest(long accruedInterest) {
        this.accruedInterest = accruedInterest;
    }

    public long getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(long couponRate) {
        this.couponRate = couponRate;
    }

    public byte[] getFiller42() {
        return filler42;
    }

    public void setFiller42(byte[] filler42) {
        this.filler42 = filler42;
    }

    public long getConversionRatio() {
        return conversionRatio;
    }

    public void setConversionRatio(long conversionRatio) {
        this.conversionRatio = conversionRatio;
    }

    public int getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(int strikePrice) {
        this.strikePrice = strikePrice;
    }

    public int getStrikePrice2() {
        return strikePrice2;
    }

    public void setStrikePrice2(int strikePrice2) {
        this.strikePrice2 = strikePrice2;
    }

    public int getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(int maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getCallPutFlag() {
        return callPutFlag;
    }

    public void setCallPutFlag(String callPutFlag) {
        this.callPutFlag = callPutFlag;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public byte[] getFiller33() {
        return filler33;
    }

    public int getNoUnderlyingSecurities() {
        return noUnderlyingSecurities;
    }

    public void setNoUnderlyingSecurities(int noUnderlyingSecurities) {
        this.noUnderlyingSecurities = noUnderlyingSecurities;
    }

    public List<Entry> getUnderlyingSecurities() {
        return underlyingSecurities;
    }

    public void setUnderlyingSecurities(List<Entry> underlyingSecurities) {
        this.underlyingSecurities = underlyingSecurities;
    }

    public String getWarrantType() {
        return warrantType;
    }

    public void setWarrantType(String warrantType) {
        this.warrantType = warrantType;
    }

    public int getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(int callPrice) {
        this.callPrice = callPrice;
    }

    public short getDecimalsInCallPrice() {
        return decimalsInCallPrice;
    }

    public void setDecimalsInCallPrice(short decimalsInCallPrice) {
        this.decimalsInCallPrice = decimalsInCallPrice;
    }

    public int getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(int entitlement) {
        this.entitlement = entitlement;
    }

    public short getDecimalsInEntitlement() {
        return decimalsInEntitlement;
    }

    public void setDecimalsInEntitlement(short decimalsInEntitlement) {
        this.decimalsInEntitlement = decimalsInEntitlement;
    }

    public long getNoWarrantsPerEntitlement() {
        return noWarrantsPerEntitlement;
    }

    public void setNoWarrantsPerEntitlement(long noWarrantsPerEntitlement) {
        this.noWarrantsPerEntitlement = noWarrantsPerEntitlement;
    }

    public void setFiller33(byte[] filler33) {
        this.filler33 = filler33;
    }
}
