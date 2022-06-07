package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class StatisticsMessageParser extends OmdMessageParser<StatisticsMessage> {

	@Override
	protected StatisticsMessage createMessageInstance() {
		return new StatisticsMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.STATISTICS;
	}

	@Override
	protected StatisticsMessage doParseMessageBody(ByteBuf msgBodyBuf, StatisticsMessage msg) throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.sharesTraded = msgBodyBuf.readLongLE(); // uint64
		msg.turnover = msgBodyBuf.readLongLE(); // int64, 3 implied decimal places
		msg.highPrice = msgBodyBuf.readIntLE(); // int32, 3 implied decimal places
		msg.lowPrice = msgBodyBuf.readIntLE(); // int32, 3 implied decimal places
		msg.lastPrice = msgBodyBuf.readIntLE(); // int32, 3 implied decimal places
		msg.vwap = msgBodyBuf.readIntLE(); // int32, 3 implied decimal places
		msg.shortSellSharesTraded = msgBodyBuf.readUnsignedIntLE(); // uint32
		msg.shortSellTurnover = msgBodyBuf.readLongLE(); // int64, 3 implied decimal places
		//
		return msg;
	}

}
