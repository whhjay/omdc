package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class SecurityDefinitionMessageParser extends OmdMessageParser<SecurityDefinitionMessage> {

    @Override
    protected SecurityDefinitionMessage createMessageInstance() {
        return new SecurityDefinitionMessage();
    }

    @Override
    protected int getMsgType() {
        return OmdConstants.MSG_TYPE.SECURITY_DEFINITION;
    }

    @Override
    protected SecurityDefinitionMessage doParseMessageBody(ByteBuf msgBodyBuf, SecurityDefinitionMessage msg)
            throws Exception {
        msg.securityCode = msgBodyBuf.readIntLE(); // uint32
        msg.marketCode = readAsciiString(msgBodyBuf, 4); // string4
        msg.isinCode = readAsciiString(msgBodyBuf, 12); // string12
        msg.instrumentType = readAsciiString(msgBodyBuf, 4); // string4
        msgBodyBuf.readBytes(msg.filler2); // byte2
        //
        msg.spreadTableCode = readAsciiString(msgBodyBuf, 2); // string2
        msg.securityShortName = readAsciiString(msgBodyBuf, 40); // string40
        msg.currencyCode = readAsciiString(msgBodyBuf, 3); // string3
        msg.securityNameGCCS = readUtf16LEString(msgBodyBuf, 60);    // string60
        msg.securityNameGB = readUtf16LEString(msgBodyBuf, 60);        // string60
        msg.lotSize = msgBodyBuf.readUnsignedIntLE();    // uint32
        msgBodyBuf.readBytes(msg.filler4); // byte4
        //
        msg.previousClosingPrice = msgBodyBuf.readIntLE(); // int32
        msg.vcmFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.shortSellFlag = readAsciiString(msgBodyBuf, 1); // string1
        msg.casFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.ccassFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.dummySecurityFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.testSecurityFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.stampDutyFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.filler1 = msgBodyBuf.readByte(); // byte1
        //
        msg.listingDate = msgBodyBuf.readIntLE();    // uint32
        msg.delistingDate = msgBodyBuf.readIntLE();    // uint32
        msg.freeText = readAsciiString(msgBodyBuf, 38);    // string38
        msgBodyBuf.readBytes(msg.filler82); // byte82
        //
        msg.efnFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.accruedInterest = msgBodyBuf.readUnsignedIntLE();    // uint32
        msg.couponRate = msgBodyBuf.readUnsignedIntLE(); // uint32
        msgBodyBuf.readBytes(msg.filler42);
        //
        msg.conversionRatio = msgBodyBuf.readUnsignedIntLE();    // uint32
        msg.strikePrice = msgBodyBuf.readIntLE();    // int32
        msg.strikePrice2 = msgBodyBuf.readIntLE();    // int32
        msg.maturityDate = msgBodyBuf.readIntLE();    // uint32
        msg.callPutFlag = readAsciiString(msgBodyBuf, 1);    // string1
        msg.style = readAsciiString(msgBodyBuf, 1);    // string1
        msg.warrantType = readAsciiString(msgBodyBuf, 1);    // string1
        msg.callPrice = msgBodyBuf.readIntLE();    // int32
        msg.decimalsInCallPrice = msgBodyBuf.readUnsignedByte(); // unit8
        msg.entitlement = msgBodyBuf.readIntLE(); // int32
        msg.decimalsInEntitlement = msgBodyBuf.readUnsignedByte(); // unit8
        msg.noWarrantsPerEntitlement = msgBodyBuf.readUnsignedIntLE(); // uint32
        msgBodyBuf.readBytes(msg.filler33);    // byte33
        msg.noUnderlyingSecurities = msgBodyBuf.readUnsignedShortLE(); // uint16
        //
        if (msg.noUnderlyingSecurities > 0) {
            msg.underlyingSecurities = new ArrayList<>(msg.noUnderlyingSecurities);
            for (int i = 0; i < msg.noUnderlyingSecurities; i++) {
                SecurityDefinitionMessage.Entry entry = new SecurityDefinitionMessage.Entry();
                entry.underlyingSecurityCode = msgBodyBuf.readIntLE();    // uint32
                entry.underlyingSecurityWeight = msgBodyBuf.readUnsignedIntLE();    // uint32
                msg.underlyingSecurities.add(entry);
            }
        }
        //
        return msg;
    }

}
