package com.angus.omdc.message;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

/**
 * 消息解析器
 * @author wangjia
 *
 * @param <T> 消息定义类
 */
public abstract class OmdMessageParser<T extends OmdMessage> {
	/**
	 * 解析消息内容，返回消息Bean
	 * @param msgHeader
	 * @param msgBodyBuf
	 * @return
	 * @throws Exception
	 */
	public T parseMessageBody(OmdMessage msgHeader, ByteBuf msgBodyBuf) throws Exception {
		if (msgHeader.getMsgType() != getMsgType()) {
			throw new IllegalArgumentException("消息类型不匹配，期望"+getMsgType()+"，实际"+msgHeader.getMsgType());
		}
		T msg = createMessageInstance();
		msg.msgSize = msgHeader.msgSize;
		msg.msgType = msgHeader.msgType;
		msg.seqNum = msgHeader.seqNum;
		msg.setSendTime(msgHeader.getSendTime());
		return doParseMessageBody(msgBodyBuf, msg);
	}
	/**
	 * 读取指定长度的字节，转换成ascii字符串，截除首尾空格
	 * @param msgBodyBuf
	 * @param len
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String readAsciiString(ByteBuf msgBodyBuf, int len) throws UnsupportedEncodingException {
		byte[] bytes = new byte[len];
		msgBodyBuf.readBytes(bytes);
		//
		String s = new String(bytes, "ascii");
		s = s.trim();
		return s;
	}
	/**
	 * 读取指定长度的字节，转换成UTF-16LE字符串，截除首尾空格
	 * @param msgBodyBuf
	 * @param len
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String readUtf16LEString(ByteBuf msgBodyBuf, int len) throws UnsupportedEncodingException {
		byte[] bytes = new byte[len];
		msgBodyBuf.readBytes(bytes);
		//
		String s = new String(bytes, "UTF-16LE");
		s = s.trim();
		return s;
	}
	
	/**
	 * 返回空的消息实例
	 * @return
	 */
	protected abstract T createMessageInstance();
	
	/**
	 * 返回解析器对应的msgType
	 * @return
	 */
	protected abstract int getMsgType();
	/**
	 * 执行消息解析工作
	 * @param msgBodyBuf
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	protected abstract T doParseMessageBody(ByteBuf msgBodyBuf, T msg) throws Exception;
}
