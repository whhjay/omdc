package com.angus.omdc.message;

import com.angus.omdc.common.OmdConstants;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;


/**
 * OMDC following types  parser
 * <p>String  ASCII characters which are left aligned and padded with spaces, unless otherwise specified</p>
 * <p>Uint8   8 bit unsigned integer</p>
 * <p>Uint16  Little-Endian encoded 16 bit unsigned integer</p>
 * <p>Uint32  Little-Endian encoded 32 bit unsigned integer</p>
 * <p>Uint64  Little-Endian encoded 64 bit unsigned integer</p>
 * <p>Int16   Little-Endian encoded 16 bit signed integer</p>
 * <p>Int32   Little-Endian encoded 32 bit signed integer</p>
 * <p>Int64   Little-Endian encoded 64 bit signed integer</p>
 * <p>Binary  Unicode encoding used for Chinese characters</p>
 *
 * @author wangjia
 */
public class OmdPacketParser {

    private static final long unixNanoPower = 1000000;

    public static OmdPacket parse(ByteBuf buf) {
        OmdPacket packet = new OmdPacket();
        packet.pktSize = buf.readUnsignedShortLE();
        packet.msgCount = buf.readUnsignedByte();
        packet.filler = buf.readByte();
        packet.seqNum = buf.readUnsignedIntLE();
        packet.sendTime = buf.readLongLE();
        parseMessages(packet, buf);
        return packet;
    }

    public byte[] parseToBytes(ByteBuf buf) {
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        return req;
    }

    private static void parseMessages(OmdPacket packet, ByteBuf buf) {
        if (packet.getMsgCount() <= 0) {
            // 没有包含消息，可能是心跳包
            return;
        }
        long sendTime = packet.sendTime / unixNanoPower;
        List<OmdMessage> messages = new ArrayList<>(packet.getMsgCount());
        long seqNum = packet.getSeqNum();
        OmdMessage msg;
        ByteBuf msgBodyBuf;
        for (int i = 0; i < packet.getMsgCount(); i++) {
            msg = new OmdMessage();
            msg.setSendTime(sendTime);
            msg.msgSize = buf.readUnsignedShortLE();
            msg.msgType = buf.readUnsignedShortLE();
            msg.setSeqNum(seqNum); // 第一个消息的序列号就是封包的序列号
            seqNum++;
            int msgBodySize = msg.msgSize - 4;
            if (msgBodySize > 0) {
                // 读取消息内容
                msgBodyBuf = buf.readBytes(msgBodySize);
                msgBodyBuf.markReaderIndex();
                try {
                    msg = parseMessageBody(msg, msgBodyBuf);
                } catch (Exception ex) {
                    msgBodyBuf.resetReaderIndex();
                    continue;
                }
            }
            messages.add(msg);
        }
        packet.setMessages(messages);
    }

    private static OmdMessage parseMessageBody(OmdMessage msg, ByteBuf msgBodyBuf) throws Exception {
        OmdMessageParser<?> parser = OmdConstants.getOmdMessageParser(msg.getMsgType());
        if (parser == null) {
            return msg;
        }
        return parser.parseMessageBody(msg, msgBodyBuf);
    }

}
