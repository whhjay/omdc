package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class DeleteOddLotOrderMessageParser extends OmdMessageParser<DeleteOddLotOrderMessage> {

	@Override
	protected DeleteOddLotOrderMessage createMessageInstance() {
		return new DeleteOddLotOrderMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.DELETE_ODD_LOT_ORDER;
	}

	@Override
	protected DeleteOddLotOrderMessage doParseMessageBody(ByteBuf msgBodyBuf, DeleteOddLotOrderMessage msg)
			throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.orderId = msgBodyBuf.readLongLE(); // uint64
		msg.brokerId = msgBodyBuf.readUnsignedShortLE(); // uint16
		msg.side = msgBodyBuf.readUnsignedShortLE(); // uint16
		//
		return msg;
	}

}
