package com.huyan.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.unix.Socket;
import io.netty.handler.codec.http.HttpServerCodec;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��17�� ����12:31:14 
  * @Description:��ʼ������channelע��󣬻�ִ����Ӧ�ĳ�ʼ������
  */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		//ͨ��SocketChannelȥ��ö�Ӧ�Ĺܵ�
		ChannelPipeline pipeline = channel.pipeline();
		
		//ͨ���ܵ������handler
		//HttpServerCodec����netty�Լ��ṩ�������࣬Ҳ���Ǹ���������
		//���󵽷���ˣ����������룬��Ӧ���ͻ���������
		pipeline.addLast("HttpServerCodec",new HttpServerCodec());
		
		//����Զ���������࣬����"hello netty~"
		pipeline.addLast("customHandler", new CustomHandler());
	}
	
}
