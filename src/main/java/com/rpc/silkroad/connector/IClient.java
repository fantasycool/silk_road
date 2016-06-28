package com.rpc.silkroad.connector;

import com.rpc.silkroad.exception.RpcException;

/**
 * Created by frio on 16/6/28.
 */
public interface IClient extends IChannel{
    /**
     * reconnect.
     */
    void reconnect() throws RpcException;
}
