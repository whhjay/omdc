package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class TradeTickerMessageParser extends OmdMessageParser<TradeTickerMessage> {

	@Override
	protected TradeTickerMessage createMessageInstance() {
		return new TradeTickerMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.TRADE_TICKER;
	}

	@Override
	protected TradeTickerMessage doParseMessageBody(ByteBuf msgBodyBuf, TradeTickerMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.tickerId = msgBodyBuf.readUnsignedIntLE(); // uint32
		msg.price = msgBodyBuf.readIntLE(); // int32
		msg.aggregateQuantity = msgBodyBuf.readLongLE(); // uint64
		msg.tradeTime = msgBodyBuf.readLongLE(); // uint64
		msg.trdType = msgBodyBuf.readShort(); // int16
		msg.trdCancelFlag = readAsciiString(msgBodyBuf, 1); // string1
		msg.filler = msgBodyBuf.readByte(); // byte1
		//
		return msg;
	}

}
