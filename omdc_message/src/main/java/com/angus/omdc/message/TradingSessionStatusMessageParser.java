package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class TradingSessionStatusMessageParser extends OmdMessageParser<TradingSessionStatusMessage> {

	@Override
	protected TradingSessionStatusMessage createMessageInstance() {
		return new TradingSessionStatusMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.TRADING_SESSION_STATUS;
	}

	@Override
	protected TradingSessionStatusMessage doParseMessageBody(ByteBuf msgBodyBuf, TradingSessionStatusMessage msg)
			throws Exception {
		msg.marketCode = readAsciiString(msgBodyBuf, 4); // string4
		msg.tradingSessionId = msgBodyBuf.readUnsignedByte(); // uint8
		msg.tradingSessionSubId = msgBodyBuf.readUnsignedByte(); // uint8
		msg.tradingSesStatus = msgBodyBuf.readUnsignedByte(); // uint8
		msg.tradingSesControlFlag = readAsciiString(msgBodyBuf, 1); // string1
		msgBodyBuf.readBytes(msg.filler4); // byte4
		msg.startDateTime = msgBodyBuf.readLong(); // uint64
		msg.endDateTime = msgBodyBuf.readLong(); // uint64
		//
		return msg;
	}

}
