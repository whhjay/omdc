package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class IndicativeEquilibriumPriceMessageParser extends OmdMessageParser<IndicativeEquilibriumPriceMessage> {

    @Override
    protected IndicativeEquilibriumPriceMessage createMessageInstance() {
        return new IndicativeEquilibriumPriceMessage();
    }

    @Override
    protected int getMsgType() {
        return OmdConstants.MSG_TYPE.INDICATIVE_EQUILIBRIUM_PRICE;
    }

    @Override
    protected IndicativeEquilibriumPriceMessage doParseMessageBody(ByteBuf msgBodyBuf, IndicativeEquilibriumPriceMessage msg) throws Exception {
        msg.securityCode = msgBodyBuf.readIntLE(); // uint32
        msg.price = msgBodyBuf.readIntLE(); // int32
        msg.aggregateQuantity = msgBodyBuf.readLongLE();
        //
        return msg;
    }

}
