package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class SecurityStatusMessageParser extends OmdMessageParser<SecurityStatusMessage> {

	@Override
	protected SecurityStatusMessage createMessageInstance() {
		return new SecurityStatusMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.SECURITY_STATUS;
	}

	@Override
	protected SecurityStatusMessage doParseMessageBody(ByteBuf msgBodyBuf, SecurityStatusMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.suspensionIndicator = msgBodyBuf.readUnsignedByte(); // uint8
		msgBodyBuf.readBytes(msg.filler3);
		//
		return msg;
	}

}
