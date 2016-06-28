package com.rpc.silkroad.connector;

import com.rpc.silkroad.exception.RpcException;

/**
 * Created by frio on 16/6/28.
 */
public interface IChannelHandler {
    /**
     * on channel connected.
     *
     * @param channel channel.
     */
    void connected(IChannel channel) throws RpcException;

    /**
     * on channel disconnected.
     *
     * @param channel channel.
     */
    void disconnected(IChannel channel) throws RpcException;

    /**
     * on message sent.
     *
     * @param channel channel.
     * @param message message.
     */
    void sent(IChannel channel, Object message) throws RpcException;

    /**
     * on message received.
     *
     * @param channel channel.
     * @param message message.
     */
    void received(IChannel channel, Object message) throws RpcException;

    /**
     * on exception caught.
     * invoker when an exception was raised by
     *
     * @param channel channel.
     * @param exception exception.
     */
    void caught(IChannel channel, Throwable exception) throws RpcException;
}
