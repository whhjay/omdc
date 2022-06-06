package com.angus.omdc.message;

/**
 * Indicative Equilibrium Price (41)
 * 
 * The Indicative Equilibrium Price (IEP) message is generated whenever there is change of the Indicative Equilibrium Price
 * (IEP) or Indicative Equilibrium Volume (IEV) during the Pre-Opening Session (POS) or Closing Auction Session (CAS). The
 * IEP will become 0 when IEP does not exist.
 * 
 * @author wangjia
 *
 */
public class IndicativeEquilibriumPriceMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	int price; // int32, IEP, 3 implied decimal places, value is 0 if IEP is not available
	long aggregateQuantity; // uint64, IEV
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
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
}
