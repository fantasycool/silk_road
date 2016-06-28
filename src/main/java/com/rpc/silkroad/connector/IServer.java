package com.rpc.silkroad.connector;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * Created by frio on 16/6/28.
 */
public interface IServer {
    /**
     * is bound. represent if server is bound to local address
     *
     * @return bound
     */
    boolean isBound();

    /**
     * get channels.
     *
     * @return channels
     */
    Collection<IChannel> getChannels();

    /**
     * get channel.
     *
     * @param remoteAddress
     * @return channel
     */
    IChannel getChannel(InetSocketAddress remoteAddress);
}
