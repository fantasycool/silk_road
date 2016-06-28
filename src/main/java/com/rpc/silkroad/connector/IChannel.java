package com.rpc.silkroad.connector;

import com.rpc.silkroad.exception.RpcException;

import java.net.InetSocketAddress;
import java.net.URL;

/**
 * Created by frio on 16/6/28.
 */
public interface IChannel {
    /**
     * get url.
     *
     * @return url
     */
    URL getUrl();

    /**
     * get channel handler.
     *
     * @return channel handler
     */
    IChannelHandler getChannelHandler();

    /**
     * get local address.
     *
     * @return local address.
     */
    InetSocketAddress getLocalAddress();

    /**
     * send message.
     *
     * @param message
     * @throws com.rpc.silkroad.exception.RpcException
     */
    void send(Object message) throws RpcException;

    /**
     * close the channel.
     */
    void close();

    /**
     * Graceful close the channel.
     */
    void close(int timeout);

    /**
     * is closed.
     *
     * @return closed
     */
    boolean isClosed();

    /**
     * get remote address.
     *
     * @return remote address.
     */
    InetSocketAddress getRemoteAddress();

    /**
     * is connected.
     *
     * @return connected
     */
    boolean isConnected();
}
