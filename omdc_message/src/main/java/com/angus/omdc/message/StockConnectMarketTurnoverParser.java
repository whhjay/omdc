package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class StockConnectMarketTurnoverParser extends OmdMessageParser<StockConnectMarketTurnover> {

	@Override
	protected StockConnectMarketTurnover createMessageInstance() {
		return new StockConnectMarketTurnover();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.STOCK_CONNECT_MARKET_TURNOVER;
	}

	@Override
	protected StockConnectMarketTurnover doParseMessageBody(ByteBuf msgBodyBuf, StockConnectMarketTurnover msg)
			throws Exception {
		// TODO Auto-generated method stub
		msg.stockConnectMarket = readAsciiString(msgBodyBuf, 2); // string2
		msg.tradingDirection = readAsciiString(msgBodyBuf, 2); // string2
		msg.buyTurnover = msgBodyBuf.readLong(); // int64
		msg.sellTurnover = msgBodyBuf.readLong(); // int64
		msg.totalTurnover = msgBodyBuf.readLong(); // int64
		return msg;
	}

}
