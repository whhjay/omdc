package com.angus.omdc.annotation;

/**
 * 仅做标识用，表示当前消息结构包含可变长度属性
 *
 * @author wangjia
 */
public @interface DynamicLengthMessage {

    String value();

}