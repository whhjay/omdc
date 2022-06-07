package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class ReferencePriceMessageParser extends OmdMessageParser<ReferencePriceMessage> {

	@Override
	protected ReferencePriceMessage createMessageInstance() {
		return new ReferencePriceMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.REFERENCE_PRICE;
	}

	@Override
	protected ReferencePriceMessage doParseMessageBody(ByteBuf msgBodyBuf, ReferencePriceMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.referencePrice = msgBodyBuf.readIntLE();
		msg.lowerPrice = msgBodyBuf.readIntLE();
		msg.upperPrice = msgBodyBuf.readIntLE();
		//
		return msg;
	}

}
