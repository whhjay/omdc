package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class MarketDefinitionMessageParser extends OmdMessageParser<MarketDefinitionMessage> {

	@Override
	protected MarketDefinitionMessage createMessageInstance() {
		return new MarketDefinitionMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.MARKET_DEFINITION;
	}

	@Override
	protected MarketDefinitionMessage doParseMessageBody(ByteBuf msgBodyBuf, MarketDefinitionMessage msg)
			throws Exception {
		msg.marketCode = readAsciiString(msgBodyBuf, 4); // string4
		msg.marketName = readAsciiString(msgBodyBuf, 25); // string25
		msg.currencyCode = readAsciiString(msgBodyBuf, 3); // string3
		msg.numberOfSecurities = msgBodyBuf.readUnsignedIntLE(); // uint32
		//
		return msg;
	}

}
