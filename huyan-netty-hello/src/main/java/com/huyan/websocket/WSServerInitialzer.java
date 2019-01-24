package com.huyan.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��18�� ����8:13:17 
  * @Description:
  */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		//websocket http �������
		pipeline.addLast(new HttpServerCodec());
		
		//д��������֧��
		pipeline.addLast(new ChunkedWriteHandler());
		
		//��httpMessage���оۺϣ��ۺϳ�FullHttpRequest��FullHttpResponse
		//������netty�еı�̣������õ���handler
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		//����Ĵ�������֧��http��
		//����Ĵ�����֧��httpwebSocket
		/**
		 * websocket �����������Э�飬����ָ�����ͻ������ӷ��ʵ�·�� : /ws
		 * ����㴦�����ֶ����� handshaking��close, ping, pong�� ping + pong = ����
		 * ����websocket������������frames���д���ģ���ͬ���������Ͷ�Ӧ��framesҲ��ͬ
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		//�Զ����handler
		pipeline.addLast(new ChatHandler());
	}
	
}
