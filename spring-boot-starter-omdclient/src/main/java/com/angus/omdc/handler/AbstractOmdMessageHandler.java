package com.angus.omdc.handler;

import com.angus.omdc.message.OmdMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class AbstractOmdMessageHandler extends SimpleChannelInboundHandler<OmdMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, OmdMessage msg) throws Exception {
		messageReceived(ctx, msg);
	}
	
	protected abstract void messageReceived(ChannelHandlerContext ctx, OmdMessage msg);

}
