package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class BrokerQueueMessageParser extends OmdMessageParser<BrokerQueueMessage> {

	@Override
	protected BrokerQueueMessage createMessageInstance() {
		return new BrokerQueueMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.BROKER_QUEUE;
	}

	@Override
	protected BrokerQueueMessage doParseMessageBody(ByteBuf msgBodyBuf, BrokerQueueMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.itemCount = msgBodyBuf.readUnsignedByte(); // uint8
		msg.side = msgBodyBuf.readUnsignedShortLE(); // uint16
		msg.bqMoreFlag = readAsciiString(msgBodyBuf, 1); // string1
		//
		if (msg.itemCount <= 0) {
			return msg;
		}
		msg.entries = new ArrayList<>(msg.itemCount);
		for (int i = 0; i < msg.itemCount; i++) {
			BrokerQueueMessage.Entry entry = new BrokerQueueMessage.Entry();
			entry.item = msgBodyBuf.readUnsignedShortLE();
			entry.type = readAsciiString(msgBodyBuf, 1);
			entry.filler = msgBodyBuf.readByte();
			//
			msg.entries.add(entry);
		}
		return msg;
	}

}
