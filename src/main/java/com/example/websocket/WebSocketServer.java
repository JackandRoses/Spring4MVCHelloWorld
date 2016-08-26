package com.example.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

public class WebSocketServer {
	public static void main(String[] args) {
		int i = 1;
		ServerSocket server;
		InputStream in = null;
		OutputStream out = null;
		try {
			server = new ServerSocket(80);
			System.out.println("Server has started on 127.0.0.1:80.\r\nWaiting for a connection...");
			while (true) {
				System.out.println(i++);
				Socket client = server.accept();
				System.out.println("A client connected.");
				out = client.getOutputStream();
				in = client.getInputStream();
				String data = new Scanner(in, "UTF-8").useDelimiter("\\r\\n\\r\\n").next();
				Matcher get = Pattern.compile("^GET").matcher(data);
				if(get.find()){
					Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
					match.find();
					byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n" + "Connection: Upgrade\r\n" + "Upgrade: websocket\r\n" + "Sec-WebSocket-Accept: "
							+ DatatypeConverter.printBase64Binary(MessageDigest.getInstance("SHA-1").digest((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8"))) + "\r\n\r\n")
							.getBytes("UTF-8");
					out.write(response, 0, response.length);
				}
				//out.write("Hello".getBytes("UTF-8"), 0, "Hello".getBytes("UTF-8").length);
				new Thread(new HandleClientInRunnable(client)).start();
				new Thread(new HandleClientOutRunnable(client)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
