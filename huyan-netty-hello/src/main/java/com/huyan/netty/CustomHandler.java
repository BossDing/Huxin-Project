package com.huyan.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��17�� ����12:37:40 
  * @Description:�����Զ���������
  */
//SimpleChannelInboundHandler:���������������൱��[��վ���뾳]
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		//��ȡchannel
		Channel channel = ctx.channel();
		
		if (msg instanceof HttpRequest ) {
		
		//��ʾ�ͻ��˵�Զ�̵�ַ
		System.out.println(channel.remoteAddress());
		
		ByteBuf content = Unpooled.copiedBuffer("Hello netty~",CharsetUtil.UTF_8);
		
		//����һ��http response
		FullHttpResponse response = 
				new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
		response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
		
		ctx.writeAndFlush(msg);
		
		}
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel...��Ծ");
		super.channelActive(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel...����Ծ");
		super.channelInactive(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelID��ȡ��ϡ�����");
		super.channelReadComplete(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel...ע��");
		super.channelRegistered(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelUnregistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel...�Ƴ�");
		super.channelUnregistered(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelWritabilityChanged(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel��д�ɸ���");
		super.channelWritabilityChanged(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("�����쳣");
		super.exceptionCaught(ctx, cause);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("�û��¼�����������");
		super.userEventTriggered(ctx, evt);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("����������");
		super.handlerAdded(ctx);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#handlerRemoved(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("�������Ƴ�");
		super.handlerRemoved(ctx);
	}
}