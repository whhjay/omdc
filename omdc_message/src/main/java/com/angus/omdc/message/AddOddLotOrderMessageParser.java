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
        msg.securityCode = msgBodyBuf.readInt();
        msg.orderId = msgBodyBuf.readLong();
        msg.price = msgBodyBuf.readInt();
        msg.quantity = msgBodyBuf.readUnsignedInt();
        msg.brokerId = msgBodyBuf.readUnsignedShort();
        msg.side = msgBodyBuf.readUnsignedShort();
        return msg;
    }

}
