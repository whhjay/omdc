package com.angus.omdc.message;


import com.angus.omdc.annotation.DynamicLengthMessage;

import java.util.List;

/**
 * Broker Queue (54)
 * 
 * The Broker Queue message contains the priority list of the (max) top 40 broker IDs for a given side, and is generated
 * whenever any of the entries in the list are modified. Entries are ordered according to distance away from the best price with
 * best price brokers being at the front of the queue. The queue will also include spread level entries between each price level,
 * which are marked when the Type field is set to ‘S’. When the Type field is set to ‘S’, there are two possibilities;
 *  The Item is non-zero – indicating the number of spread levels away from the best price
 *  The Item is zero – indicating that there are no brokers with orders at the spread level indicated by the previous
 * entry of Type set to ‘S’
 * Example: if the active offers are as below, and assuming a spread level is 0.01:
 * 
 * Ask Price Broker ID
 * 20.28 2137
 * 20.28 4138
 * 20.29 2141
 * 20.29 5123
 * 20.31 3145
 * 
 * Then the resulting Ask side Broker Queue will be represented as below:
 * 
 * Entry 1 2 3 4 5 6 7 8 9
 * Item 2137 4138 1 2141 5123 2 0 3 3145
 * Type B B S B B S S S B
 * 
 * The Conflated Broker Queue Feed ("CBQ") which is included in SS (OMD Securities Standard), is provided to the Licensee
 * of SP (OMD Securities Premium) as a complimentary service. The service provides broker queue information in conflated
 * mode whilst SP provides market data in streaming mode. The service level between CBQ and SP is therefore inherently
 * different by nature and the information in these two products is not synchronized. Licensed vendors are therefore reminded
 * that if they plan to provide the CBQ along with the market depth available from SP, appropriate disclaimers and warnings
 * should be provided to subscribers highlighting the service level difference.
 * 
 * @author wangjia
 *
 */
@DynamicLengthMessage("12 + 4nI (nI = value of ItemCount)")
public class BrokerQueueMessage extends OmdMessage {
	int securityCode;	// uint32, 1-99999
	short itemCount;	// uint8, 0-40
	int	side;			// uint16, 1:Buy, 2:Sell
	String bqMoreFlag;	// string1, Y: More broker numbers exist in the queue, N: No more exist
	List<Entry> entries; // 长度由itemCount决定
	
	public static class Entry {
		/**
		 * This field contains either the broker number or the number of
		 * spreads away from the best price.
		 */
		int item;	// uint16, 
		/**
		 * B: Broker number
		 * S: Number of Spread
		 */
		String type;	// string1, 
		byte filler;
		public int getItem() {
			return item;
		}
		public void setItem(int item) {
			this.item = item;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public byte getFiller() {
			return filler;
		}
		public void setFiller(byte filler) {
			this.filler = filler;
		}
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public short getItemCount() {
		return itemCount;
	}

	public void setItemCount(short itemCount) {
		this.itemCount = itemCount;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getBqMoreFlag() {
		return bqMoreFlag;
	}

	public void setBqMoreFlag(String bqMoreFlag) {
		this.bqMoreFlag = bqMoreFlag;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
}
