package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class ClosingPriceMessageParser extends OmdMessageParser<ClosingPriceMessage> {

	@Override
	protected ClosingPriceMessage createMessageInstance() {
		return new ClosingPriceMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.CLOSING_PRICE;
	}

	@Override
	protected ClosingPriceMessage doParseMessageBody(ByteBuf msgBodyBuf, ClosingPriceMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE();
		msg.closingPrice = msgBodyBuf.readIntLE(); // int32, 3 implied decimal places
		msg.numberOfTrades = msgBodyBuf.readUnsignedIntLE();
		
		return msg;
	}

}
