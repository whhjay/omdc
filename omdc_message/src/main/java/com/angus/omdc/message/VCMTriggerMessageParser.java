package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class VCMTriggerMessageParser extends OmdMessageParser<VCMTriggerMessage> {

	@Override
	protected VCMTriggerMessage createMessageInstance() {
		return new VCMTriggerMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.VCM_TRIGGER;
	}

	@Override
	protected VCMTriggerMessage doParseMessageBody(ByteBuf msgBodyBuf, VCMTriggerMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.collingOffStartTime = msgBodyBuf.readLongLE(); // uint64
		msg.collingOffEndTime = msgBodyBuf.readLongLE(); // uint64
		msg.vcmREferencePrice = msgBodyBuf.readIntLE(); // int32
		msg.vcmLowerPrice = msgBodyBuf.readIntLE(); // int32
		msg.vcmUpperPrice = msgBodyBuf.readIntLE(); // int32
		//
		return msg;
	}

}
