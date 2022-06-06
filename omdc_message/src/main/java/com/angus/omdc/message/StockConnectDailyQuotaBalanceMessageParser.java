package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class StockConnectDailyQuotaBalanceMessageParser extends OmdMessageParser<StockConnectDailyQuotaBalanceMessage> {

	@Override
	protected StockConnectDailyQuotaBalanceMessage createMessageInstance() {
		return new StockConnectDailyQuotaBalanceMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.STOCK_CONNECT_DAILY_QUOTA_BALANCE;
	}

	@Override
	protected StockConnectDailyQuotaBalanceMessage doParseMessageBody(ByteBuf msgBodyBuf,
			StockConnectDailyQuotaBalanceMessage msg) throws Exception {
		msg.stockConnectMarket = readAsciiString(msgBodyBuf, 2); // string2
		msg.tradingDirection = readAsciiString(msgBodyBuf, 2); // string2
		msg.dailyQuotaBalance = msgBodyBuf.readLong(); // int64
		msg.dailyQuotaBalanceTime = msgBodyBuf.readLong(); // uint64
		
//		Date dailyQuotaBalanceTime = new Date(msg.dailyQuotaBalanceTime / (1000*1000));
		return msg;
	}

}
