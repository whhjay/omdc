package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class YieldMessageParser extends OmdMessageParser<YieldMessage> {

	@Override
	protected YieldMessage createMessageInstance() {
		return new YieldMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.YIELD;
	}

	@Override
	protected YieldMessage doParseMessageBody(ByteBuf msgBodyBuf, YieldMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.yield = msgBodyBuf.readIntLE(); // int32
		//
		return msg;
	}

}
