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
		msg.securityCode = msgBodyBuf.readInt(); // uint32
		msg.sharesTraded = msgBodyBuf.readLong(); // uint64
		msg.turnover = msgBodyBuf.readLong(); // int64, 3 implied decimal places
		msg.highPrice = msgBodyBuf.readInt(); // int32, 3 implied decimal places
		msg.lowPrice = msgBodyBuf.readInt(); // int32, 3 implied decimal places
		msg.lastPrice = msgBodyBuf.readInt(); // int32, 3 implied decimal places
		msg.vwap = msgBodyBuf.readInt(); // int32, 3 implied decimal places
		msg.shortSellSharesTraded = msgBodyBuf.readUnsignedInt(); // uint32
		msg.shortSellTurnover = msgBodyBuf.readLong(); // int64, 3 implied decimal places
		//
		return msg;
	}

}
