package com.example.websocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HandleClientOutRunnable implements Runnable {
	private Socket client;

	public HandleClientOutRunnable(Socket client) {
		this.client = client;
	}

	private byte[] b = new byte[1024];

	boolean stop = true;

	@Override
	public void run() {
		InputStream in = null;
		try {
			while (true) {
				in = client.getInputStream();
				DataInputStream data = new DataInputStream(client.getInputStream());
				System.out.println(data.read(b));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (client != null) {
					client = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
