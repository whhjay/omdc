package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class IndexDefinitionMessageParser extends OmdMessageParser<IndexDefinitionMessage> {

	@Override
	protected IndexDefinitionMessage createMessageInstance() {
		return new IndexDefinitionMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.INDEX_DEFINITION;
	}

	@Override
	protected IndexDefinitionMessage doParseMessageBody(ByteBuf msgBodyBuf, IndexDefinitionMessage msg)
			throws Exception {
		msg.indexCode = readAsciiString(msgBodyBuf, 11); // string11
		msg.indexSource = readAsciiString(msgBodyBuf, 1); // string1
		msg.currencyCode = readAsciiString(msgBodyBuf, 3); // string3
		msg.filler = msgBodyBuf.readByte(); // byte
		//
		return msg;
	}

}
