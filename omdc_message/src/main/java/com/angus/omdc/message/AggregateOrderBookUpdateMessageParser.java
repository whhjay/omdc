package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class AggregateOrderBookUpdateMessageParser extends OmdMessageParser<AggregateOrderBookUpdateMessage> {

	@Override
	protected AggregateOrderBookUpdateMessage createMessageInstance() {
		return new AggregateOrderBookUpdateMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.AGGREGATE_ORDER_BOOK_UPDATE;
	}

	@Override
	protected AggregateOrderBookUpdateMessage doParseMessageBody(ByteBuf msgBodyBuf,
			AggregateOrderBookUpdateMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE();
		msgBodyBuf.readBytes(msg.filler3);
		msg.noEntries 	= msgBodyBuf.readUnsignedByte();
		if (msg.noEntries <= 0) {
			return msg;
		}
		msg.entries = new ArrayList<AggregateOrderBookUpdateMessage.Entry>(msg.noEntries);
		for (int i = 0; i < msg.noEntries; i++) {
			AggregateOrderBookUpdateMessage.Entry entry = new AggregateOrderBookUpdateMessage.Entry();
			entry.aggregateQuantity = msgBodyBuf.readLongLE(); // uint64
			entry.price = msgBodyBuf.readIntLE();
			entry.numberOfOrders = msgBodyBuf.readUnsignedIntLE();
			entry.side = msgBodyBuf.readUnsignedShortLE();
			entry.priceLevel = msgBodyBuf.readUnsignedByte();
			entry.updateAction = msgBodyBuf.readUnsignedByte();
			msgBodyBuf.readBytes(entry.filler4);
			//
			msg.entries.add(entry);
		}
		return msg;
	}

}
