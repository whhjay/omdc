package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class OrderImbalanceMessageParser extends OmdMessageParser<OrderImbalanceMessage> {

	@Override
	protected OrderImbalanceMessage createMessageInstance() {
		return new OrderImbalanceMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.ORDER_IMBALANCE;
	}

	@Override
	protected OrderImbalanceMessage doParseMessageBody(ByteBuf msgBodyBuf, OrderImbalanceMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.orderImbalanceDirection = readAsciiString(msgBodyBuf, 1); // string1
		msg.filler = msgBodyBuf.readByte(); // byte
		msg.orderImbalanceQuantity = msgBodyBuf.readLongLE(); // uint64
		msgBodyBuf.readBytes(msg.filler2); // byte2
		//
		return msg;
	}

}
