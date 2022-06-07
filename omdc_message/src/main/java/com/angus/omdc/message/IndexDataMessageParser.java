package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class IndexDataMessageParser extends OmdMessageParser<IndexDataMessage> {

	@Override
	protected IndexDataMessage createMessageInstance() {
		return new IndexDataMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.INDEX_DATA;
	}

	@Override
	protected IndexDataMessage doParseMessageBody(ByteBuf msgBodyBuf, IndexDataMessage msg) throws Exception {
		msg.indexCode = readAsciiString(msgBodyBuf, 11); // string11
		msg.indexStatus = readAsciiString(msgBodyBuf, 1); // string1
		msg.indexTime = msgBodyBuf.readLongLE(); // int64
		msg.indexValue = msgBodyBuf.readLongLE(); // int64
		msg.netChgPrevDay = msgBodyBuf.readLongLE(); // int64
		msg.highValue = msgBodyBuf.readLongLE(); // int64
		msg.lowValue = msgBodyBuf.readLongLE(); // int64
		msg.easValue = msgBodyBuf.readLongLE(); // int64
		msg.indexTurnover = msgBodyBuf.readLongLE(); // int64
		msg.openingValue = msgBodyBuf.readLongLE(); // int64
		msg.closingValue = msgBodyBuf.readLongLE(); // int64
		msg.previousSesClose = msgBodyBuf.readLongLE(); // int64
		msg.indexVolume = msgBodyBuf.readLongLE(); // int64
		msg.netChgPrevDayPct = msgBodyBuf.readIntLE(); // int32
		msg.exception = readAsciiString(msgBodyBuf, 1); // string1
		msgBodyBuf.readBytes(msg.filler3); // string3
		//
		return msg;
	}

}
