package com.rpc.silkroad.codec;

import com.rpc.silkroad.buffer.ChannelBuffer;
import com.rpc.silkroad.connector.IChannel;

import java.io.IOException;

/**
 * Codec
 * Created by frio on 16/6/28.
 */
public interface ICodec {
    /**
     * encode and write to channel
     * @param channel
     * @param buffer
     * @param message
     * @throws IOException
     */
    void encode(IChannel channel, ChannelBuffer buffer, Object message) throws IOException;

    /**
     * read and decode from channel
     * @param channel
     * @param channelBuffer
     * @return
     * @throws IOException
     */
    Object decode(IChannel channel, ChannelBuffer channelBuffer)throws IOException;
}
