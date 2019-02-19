package com.huyan.netty;

import java.util.HashMap;

import io.netty.channel.Channel;

public class UserChannelRel {
	private static HashMap<String, Channel> manager = new HashMap<>();
	
	public static void put(String senderId, Channel channel) {
		manager.put(senderId, channel);
	}
	
	public static Channel get(String senderId) {
		return manager.get(senderId);
	}
	
	public static void output() {
		for (HashMap.Entry<String, Channel> entry: manager.entrySet()) {
			System.out.println("UserId: " + entry.getKey()
							+ ", ChannelId: " + entry.getValue().id().asLongText());
		}
	}
}
