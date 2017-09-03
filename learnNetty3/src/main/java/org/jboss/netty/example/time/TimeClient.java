package org.jboss.netty.example.time;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        ChannelFactory factory =
            new NioClientSocketChannelFactory (
                    Executors.newCachedThreadPool(),
                    Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap (factory);

        TimeClientHandler handler = new TimeClientHandler();
        bootstrap.getPipeline().addLast("handler", handler);
       
        bootstrap.setOption("tcpNoDelay" , true);
        bootstrap.setOption("keepAlive", true);

        bootstrap.connect (new InetSocketAddress(host, port));
    }
}