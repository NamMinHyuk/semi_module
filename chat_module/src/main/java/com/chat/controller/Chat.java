package com.chat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// 웹 소켓의 호스트 주소 설정
@ServerEndpoint("/chat_module_test")
public class Chat  {
	
	// 접속 유저 session 을 구분하는 Set에 저장
	// 멀티 쓰레드 환경에서 사용하기 위한 Set을 동기화 해줌
	// Collections.synchronizedSet 메소드는 Set을 동기화된 Set으로 리턴
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		// 채팅방에 접속을 하면 세션이 생김
		// 현재 접속하고 있는 세션은 set에 저장
		System.out.println(session);
		clients.add(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println(message);
		synchronized (clients) {
			// 동기화 시켜줌
			for(Session client : clients) {
				// clients에 있는 세션을 하나 씩 가지고 옴
				// 만약 자신의 접속한 세션이 아닌 세션에서 메시지를 보내면 그것을 나에게 보내줌 
				if(!client.equals(session)) {
					//메시지를 전달하는 메소드
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		// 채팅방을 종료 할때 세션을 지워 줌 
		// 지우지 않으면 채팅방이 소멸이 안됨
		clients.remove(session);
	}
    

}
