package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class SequenceResetMessageParser extends OmdMessageParser<SequenceResetMessage> {

	@Override
	protected SequenceResetMessage createMessageInstance() {
		return new SequenceResetMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.SEQUENCE_RESET;
	}

	@Override
	protected SequenceResetMessage doParseMessageBody(ByteBuf msgBodyBuf, SequenceResetMessage msg) throws Exception {
		msg.newSeqNo = msgBodyBuf.readUnsignedInt(); // uint32
		//
		return msg;
	}

}
