package com.angus.omdc.message;


import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

public class AddOddLotOrderMessageParser extends OmdMessageParser<AddOddLotOrderMessage> {

    @Override
    protected int getMsgType() {
        return OmdConstants.MSG_TYPE.ADD_ODD_LOT_ORDER;
    }

    @Override
    protected AddOddLotOrderMessage createMessageInstance() {
        return new AddOddLotOrderMessage();
    }

    @Override
    protected AddOddLotOrderMessage doParseMessageBody(ByteBuf msgBodyBuf, AddOddLotOrderMessage msg) throws Exception {
        msg.securityCode = msgBodyBuf.readIntLE();
        msg.orderId = msgBodyBuf.readLongLE();
        msg.price = msgBodyBuf.readIntLE();
        msg.quantity = msgBodyBuf.readUnsignedIntLE();
        msg.brokerId = msgBodyBuf.readUnsignedShortLE();
        msg.side = msgBodyBuf.readUnsignedShortLE();
        return msg;
    }

}
