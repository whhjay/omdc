package com.angus.omdc.message;

/**
 * Order Imbalance (56)
 * 
 * he Order Imbalance message provides order imbalance information at the Indicative Equilibrium Price (IEP) during the
 * Closing Auction Session (CAS).
 * 
 * Total Length ....................................................................... 20
 * 
 * @author wangjia
 *
 */
public class OrderImbalanceMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	/**
	 * N Buy = Sell
	 * B Buy Surplus
	 * S Sell Surplus
	 * <space> Not applicable, i.e. when
	 * IEP is not available
	 */
	String orderImbalanceDirection; // string1
	byte filler;
	long orderImbalanceQuantity; // uint64
	byte[] filler2 = new byte[2];
	
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public String getOrderImbalanceDirection() {
		return orderImbalanceDirection;
	}
	public void setOrderImbalanceDirection(String orderImbalanceDirection) {
		this.orderImbalanceDirection = orderImbalanceDirection;
	}
	public byte getFiller() {
		return filler;
	}
	public void setFiller(byte filler) {
		this.filler = filler;
	}
	public long getOrderImbalanceQuantity() {
		return orderImbalanceQuantity;
	}
	public void setOrderImbalanceQuantity(long orderImbalanceQuantity) {
		this.orderImbalanceQuantity = orderImbalanceQuantity;
	}
	public byte[] getFiller2() {
		return filler2;
	}
	public void setFiller2(byte[] filler2) {
		this.filler2 = filler2;
	}
}
