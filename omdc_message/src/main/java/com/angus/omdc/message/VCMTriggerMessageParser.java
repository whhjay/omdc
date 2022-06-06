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
		msg.securityCode = msgBodyBuf.readInt(); // uint32
		msg.collingOffStartTime = msgBodyBuf.readLong(); // uint64
		msg.collingOffEndTime = msgBodyBuf.readLong(); // uint64
		msg.vcmREferencePrice = msgBodyBuf.readInt(); // int32
		msg.vcmLowerPrice = msgBodyBuf.readInt(); // int32
		msg.vcmUpperPrice = msgBodyBuf.readInt(); // int32
		//
		return msg;
	}

}
