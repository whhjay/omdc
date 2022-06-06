package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class CurrencyRateMessageParser extends OmdMessageParser<CurrencyRateMessage> {

	@Override
	protected CurrencyRateMessage createMessageInstance() {
		return new CurrencyRateMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.CURRENCY_RATE;
	}

	@Override
	protected CurrencyRateMessage doParseMessageBody(ByteBuf msgBodyBuf, CurrencyRateMessage msg) throws Exception {
		msg.currencyCode = readAsciiString(msgBodyBuf, 3); // 3
		msg.filler1 = msgBodyBuf.readByte(); // 1
		msg.currencyFactor = msgBodyBuf.readUnsignedShort(); // 2
		msgBodyBuf.readBytes(msg.filler2); // 2
		msg.currencyRate = msgBodyBuf.readUnsignedInt(); // 4
		return msg;
	}

}
