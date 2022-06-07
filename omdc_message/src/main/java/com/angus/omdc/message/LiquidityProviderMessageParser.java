package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class LiquidityProviderMessageParser extends OmdMessageParser<LiquidityProviderMessage> {

	@Override
	protected LiquidityProviderMessage createMessageInstance() {
		return new LiquidityProviderMessage();
	}

	@Override
	protected int getMsgType() {
		return OmdConstants.MSG_TYPE.LIQUIDITY_PROVIDER;
	}

	@Override
	protected LiquidityProviderMessage doParseMessageBody(ByteBuf msgBodyBuf, LiquidityProviderMessage msg)
			throws Exception {
		msg.securityCode = msgBodyBuf.readIntLE(); // uint32
		msg.noLiquidityProviders = msgBodyBuf.readUnsignedShortLE(); // uint16
		if (msg.noLiquidityProviders <= 0) {
			return msg;
		}
		msg.lpBrokerNumber = new ArrayList<Integer>(msg.noLiquidityProviders);
		for (int i = 0; i < msg.noLiquidityProviders; i++) {
			int brokerNumber = msgBodyBuf.readUnsignedShortLE();
			//
			msg.lpBrokerNumber.add(brokerNumber);
		}
		//
		return msg;
	}

}
