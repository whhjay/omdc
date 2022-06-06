package com.angus.omdc.message;

/**
 * Delete Odd Lot Order (34)
 * 
 * The Delete Odd Lot Order message is generated when an existing odd lot order identified by the OrderId is deleted.
 * 
 * @author wangjia
 *
 */
public class DeleteOddLotOrderMessage extends OmdMessage {
	int securityCode; // uint32, 1-99999
	long orderId; // uint64
	int brokerId; // uint16
	int side; // uint16, side of the order, 0:Bid 1:Offer
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
}
