package com.angus.omdc.message;

/**
 * Nominal Price (40)
 * 
 * The Nominal message may be generated when an order is added, deleted or modified in a book or when trade or trade
 * cancel is performed. Before the arrival of the first Nominal Price message, the nominal price should be the same as the
 * previous closing price provided in the Security Definition (11) message.
 * 
 * Total Length ....................................................................... 12
 * 
 * @author wangjia
 *
 */
public class NominalPriceMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	/**
	 * Note: Nominal Price may be 0 in specific cases (e.g. no reference price)
	 */
	int nominalPrice; // int32, 3 implied decimal places
	//
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getNominalPrice() {
		return nominalPrice;
	}
	public void setNominalPrice(int nominalPrice) {
		this.nominalPrice = nominalPrice;
	}
}
