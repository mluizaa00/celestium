package com.celestium.server;

import com.celestium.manager.LoggingManager;
import com.google.common.flogger.FluentLogger;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.istack.internal.Nullable;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ProtocolConnection {

    private final FluentLogger LOGGER = LoggingManager.getLogger();
    private List<ChannelFuture> listeningChannels;

    /**
     * Connects the protocol with that specific address.
     *
     * @param address IP Address that the server will connect
     * @param port Port that the server will connect
     */
    public void connect(@Nullable InetAddress address, int port) {
        this.listeningChannels = new ArrayList<>();
        synchronized (listeningChannels) {
            /*
            Checks if Epoll is available (More performatic)
             */
            Class channelType = EpollServerSocketChannel.class;
            if (!Epoll.isAvailable()) {
                channelType = NioServerSocketChannel.class;
                LOGGER.atInfo().log("Epoll not available. Using default channel type instead.");
            }

            final ChannelInitializer<Channel> channelInitializer = new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    try {
                        channel.config().setOption(ChannelOption.TCP_NODELAY, true);
                    } catch (ChannelException exception) {
                        LOGGER.atSevere().log("A error occurred while setting TCP_NODELAY option for Channel. " + exception.getMessage());
                    }
                }
            };

            /*
            Creates a new instance of the server and connects it to the address and port.
             */
            final ServerBootstrap bootstrap = new ServerBootstrap()
                .channel(channelType)
                .childHandler(channelInitializer)
                .localAddress(address, port)
                .group(new NioEventLoopGroup(0, new ThreadFactoryBuilder()
                    .setNameFormat("Netty Server IO #%d")
                    .setDaemon(true)
                    .build())
                );

            /*
            Adds instance to listening channels.
             */
            listeningChannels.add(bootstrap
                .bind()
                .syncUninterruptibly()
            );
        }
    }

}
