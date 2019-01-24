package com.huyan.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��16�� ����11:53:13 
  * @Description:ʵ�ֿͻ��˷���һ�����󣬷������᷵��hello netty
  */
public class HelloServer {
	public static void main(String[] args) throws Exception{
		
		//����һ���߳��飬���ڽ��ܿͻ��˵����ӣ����ǲ����κδ��������κ�����
		//���߳���
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//���߳��飬���߳��������񶪹�ȥ����������߳���ȥ����
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//netty�������Ĵ�����Serverbootstrap ��һ��������
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup,workerGroup)				//���������߳���
							.channel(NioServerSocketChannel.class)		//����nio��˫��ͨ��
							.childHandler(new HelloServerInitializer());						//�Ӵ����������ڴ���workerGroup
			
			//����server����������8088Ϊ�����Ķ˿ںţ�ͬʱ������ʽΪͬ��
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
			
			//�����رյ�channel������λͬ����ʽ
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
