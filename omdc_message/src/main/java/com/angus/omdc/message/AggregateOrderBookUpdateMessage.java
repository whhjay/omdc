package com.angus.omdc.message;


import com.angus.omdc.annotation.DynamicLengthMessage;

import java.util.List;

/**
 * Aggregate Order Book Update (53)
 * 
 * Refer to Section 5 - Aggregate Order Book Management for details on the Aggregate Order Book Update message. The
 * Aggregate Order Book Update message only applies to Board Lots.
 * For an UpdateAction of ’74 – Orderbook Clear’ please refer to Example 6 within the Aggregate Order Book Management
 * section 5.
 * 
 * Total Length................................................................ 12 + 24nO (nO = value of NoEntries)
 * 
 * @author wangjia
 *
 */
@DynamicLengthMessage("12 + 24nO (nO = value of NoEntries)")
public class AggregateOrderBookUpdateMessage extends OmdMessage {
	int securityCode; // 1-99999
	byte[] filler3 = new byte[3];		// 3byte
	short noEntries;		// uint8
	List<Entry> entries;
	
	// 24bytes
	public static class Entry {
		long aggregateQuantity;	// uint64
		int	price;			// int32, 3implied decimal paces
		long numberOfOrders;	// uint32
		int side;			// uint16
		short priceLevel;	// uint8
		short updateAction; // uint8
		byte[] filler4 = new byte[4]; // 4byte
		
		public long getAggregateQuantity() {
			return aggregateQuantity;
		}
		public void setAggregateQuantity(long aggregateQuantity) {
			this.aggregateQuantity = aggregateQuantity;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public long getNumberOfOrders() {
			return numberOfOrders;
		}
		public void setNumberOfOrders(long numberOfOrders) {
			this.numberOfOrders = numberOfOrders;
		}
		public int getSide() {
			return side;
		}
		public void setSide(int side) {
			this.side = side;
		}
		public short getPriceLevel() {
			return priceLevel;
		}
		public void setPriceLevel(short priceLevel) {
			this.priceLevel = priceLevel;
		}
		public short getUpdateAction() {
			return updateAction;
		}
		public void setUpdateAction(short updateAction) {
			this.updateAction = updateAction;
		}
		public byte[] getFiller4() {
			return filler4;
		}
		public void setFiller4(byte[] filler4) {
			this.filler4 = filler4;
		}
	}
	
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public byte[] getFiller3() {
		return filler3;
	}
	public void setFiller3(byte[] filler3) {
		this.filler3 = filler3;
	}
	public short getNoEntries() {
		return noEntries;
	}
	public void setNoEntries(short noEntries) {
		this.noEntries = noEntries;
	}
	public List<Entry> getEntries() {
		return entries;
	}
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
}
