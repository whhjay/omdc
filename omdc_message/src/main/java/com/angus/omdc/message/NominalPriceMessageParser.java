package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class NominalPriceMessageParser extends OmdMessageParser<NominalPriceMessage> {

	@Override
	protected NominalPriceMessage createMessageInstance() {
		return new NominalPriceMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.NOMINAL_PRICE;
	}

	@Override
	protected NominalPriceMessage doParseMessageBody(ByteBuf msgBodyBuf, NominalPriceMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.nominalPrice = msgBodyBuf.readIntLE(); // int32
		//
		return msg;
	}

}
