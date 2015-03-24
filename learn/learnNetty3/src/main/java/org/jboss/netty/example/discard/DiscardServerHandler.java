package org.jboss.netty.example.discard;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

@ChannelPipelineCoverage("all")
public class DiscardServerHandler extends SimpleChannelHandler {

//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        ChannelBuffer  buf = (ChannelBuffer) e.getMessage();
//        while(buf.readable()) {
//            System.out.println((char) buf.readByte());
//        }
//    }
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	
        Channel  ch = e.getChannel();
        ch.write(e.getMessage());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
       
        Channel ch = e.getChannel();
        ch.close();
    }
}