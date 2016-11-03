package com.ecb.game.cit.web.socket;

import org.springframework.web.socket.WebSocketSession;

public class SessionInfo {
	private WebSocketSession session;
	private String name;

	public SessionInfo(WebSocketSession session) {
		this.session = session;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
