package com.angus.omdc.message;

/**
 * Add Odd Lot Order (33)
 * 
 * The Add Odd Lot Order message is generated when a new odd lot order is inserted into the order book.
 * 
 * Total Length ....................................................................... 28
 * 
 * @author wangjia
 *
 */
public class AddOddLotOrderMessage extends OmdMessage {
	int securityCode; 	// uint32, 1-99999
	long orderId; 		// uint64
	int price; 			// int32, 3 implied decimal places
	long quantity; 		// uint32
	int brokerId;		// uint16
	int side;			// uint16, 0: Bid, 1: Offer
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
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
