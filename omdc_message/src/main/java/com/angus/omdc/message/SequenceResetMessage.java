package com.angus.omdc.message;

/**
 * Sequence Reset (100)
 * 
 * The Sequence Reset message is sent on each multicast channel at start of day. It may also be sent when there is a need for
 * the rectification of stock reference data before market open.
 * The client must ignore the sequence number of the Sequence Reset message itself, and set the next expected sequence
 * number to NewSeqNo. The client may receive multiple sequence reset messages from all channels. Whenever the
 * Sequence Reset message is received, clients must clear all cached data for all instruments traded in the Cash Market and
 * indices and then subscribe to the refresh channels to receive the current state of the market.
 * 
 *  Total Length 8
 *  
 * @author wangjia
 *
 */
public class SequenceResetMessage extends OmdMessage {
	long newSeqNo; // uint32, always set to 1
	//

	public long getNewSeqNo() {
		return newSeqNo;
	}

	public void setNewSeqNo(long newSeqNo) {
		this.newSeqNo = newSeqNo;
	}
}
