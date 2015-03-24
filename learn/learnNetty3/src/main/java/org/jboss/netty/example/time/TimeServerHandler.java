package org.jboss.netty.example.time;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

@ChannelPipelineCoverage("all")
public class TimeServerHandler extends SimpleChannelHandler {

//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        ChannelBuffer  buf = (ChannelBuffer) e.getMessage();
//        while(buf.readable()) {
//            System.out.println((char) buf.readByte());
//        }
//    }
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//    	
//        Channel  ch = e.getChannel();
//        ch.write(e.getMessage());
//    }
    @Override  
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {  
        Channel ch = e.getChannel();  
         
        ChannelBuffer time = ChannelBuffers.buffer(4);  
        time.writeInt((int) (System.currentTimeMillis() / 1000));  
         
        ChannelFuture f = ch.write(time);  
         
        f.addListener(new ChannelFutureListener() {  
            public void operationComplete(ChannelFuture future) {  
                Channel ch = future.getChannel();  
                ch.close();  
            }  
        });  
    }  
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
       
        Channel ch = e.getChannel();
        ch.close();
    }
}