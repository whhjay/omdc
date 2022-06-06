package com.angus.omdc.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteOrder;
import java.util.List;

/**
 * omdc通道数据包转码
 */
public class PacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		OmdPacketParser parser = new OmdPacketParser();
		OmdPacket packet = parser.parse(in);
//		// 解析成功的消息交给其他Handler处理
		if (packet.getMessages() != null) {
			for (OmdMessage msg : packet.getMessages()) {
				out.add(msg);
			}
		}
	}
	
	public byte[] parseToBytes(ByteBuf buf) {
		buf = buf.order(ByteOrder.LITTLE_ENDIAN);
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		return req;
	}

}
