package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class MarketTurnoverMessageParser extends OmdMessageParser<MarketTurnoverMessage> {

	@Override
	protected MarketTurnoverMessage createMessageInstance() {
		return new MarketTurnoverMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.MARKET_TURNOVER;
	}

	@Override
	protected MarketTurnoverMessage doParseMessageBody(ByteBuf msgBodyBuf, MarketTurnoverMessage msg) throws Exception {
		msg.marketCode = readAsciiString(msgBodyBuf, 4); // string4
		msg.currencyCode = readAsciiString(msgBodyBuf, 3); // string3
		msg.filler = msgBodyBuf.readByte();
		msg.turnover = msgBodyBuf.readLong();
		//
		return msg;
	}

}
