package com.example.websocket;

import groovy.json.JsonOutput;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.model.Location;

@ServerEndpoint(value = "/getPointLocation")
public class Server {
	
	private Session session;
	
	private final Location location = new Location();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("ws opened...");
		this.session = session;
		sendMessage();
	}

	private void sendMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true){
						Thread.sleep(2000);
						Location loc = calcTargetsLoaction();
						if(session!=null){
							session.getBasicRemote().sendText(JsonOutput.toJson(loc).toUpperCase());
						}
					}
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	synchronized private Location calcTargetsLoaction() {
		this.location.setAng(BigDecimal.valueOf(Math.random()*90).setScale(2, RoundingMode.HALF_UP).doubleValue());
		this.location.setR(BigDecimal.valueOf(Math.round(Math.random()*120)).setScale(2,RoundingMode.HALF_UP).doubleValue());
		return this.location;
	}

	@OnClose
	public void close(Session session) {
		try {
			this.session.close();
			System.out.println("ws closed...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.session = null;
	}

	@OnError
	public void onError(Throwable error) {
		try {
			this.session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnMessage
	public void handleMessage(Session session,String message) {
		System.out.println(message);
	}
}
