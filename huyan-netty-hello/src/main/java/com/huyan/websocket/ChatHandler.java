package com.huyan.websocket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��18�� ����8:21:52 
  * @Description:������Ϣ��handler
  * TextWebsocketFrame����netty�У�������Ϊwebsocketר�Ŵ����ı��Ķ���frame����Ϣ������
  */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	/* (non-Javadoc)
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//��ȡ�ͻ��˴����������Ϣ
		String content = msg.text();
		System.out.println("���յ������ݣ�" + content);
		
//		for (Channel channel: clients) {
//			channel.writeAndFlush(new TextWebSocketFrame("[��������]" + LocalDateTime.now() + "���յ���Ϣ����ϢΪ��" + content));
//		}
		//��������������������forѭ��һ��
		clients.writeAndFlush(new TextWebSocketFrame("[��������]" + LocalDateTime.now() + "���յ���Ϣ����ϢΪ" + content));
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.ChannelHandlerContext)
	 */
	//���ͻ������ӷ����֮�󣨴����ӣ�
	//��ȡ�ͻ��˵�channel�����ҷŵ�channelGroup��ȥ���й���
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
	}

	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//������handlerRemoved��ChannelGroup���Զ��Ƴ���Ӧ�ͻ��˵�channel
//		clients.remove(ctx.channel());
		System.out.println("�ͻ��˶Ͽ���channel��Ӧ�ĳ�idΪ��" + ctx.channel().id().asLongText());
		System.out.println("�ͻ��˶Ͽ���channel��Ӧ�Ķ�idΪ��" + ctx.channel().id().asShortText());
	}
	
}
