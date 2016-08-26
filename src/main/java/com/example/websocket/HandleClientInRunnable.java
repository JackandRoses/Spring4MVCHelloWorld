package com.example.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class HandleClientInRunnable implements Runnable {
	private Socket client;

	public HandleClientInRunnable(Socket client) {
		this.client = client;
	}

	private byte[] b = new byte[1024];

	boolean stop = true;

	@Override
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			while (true) {
				in = client.getInputStream();
				// 向客户端回复信息
				out = client.getOutputStream();
				System.out.print("请输入:\t");
				// 键盘录入
				br = new BufferedReader(new InputStreamReader(System.in));
				String send = br.readLine();
				Integer size = send.getBytes(Charset.forName("UTF-8")).length;
				byte len = 0;
				if(size>0&&size<=125){
					len = size.byteValue();
				}
				// 发送数据
//				byte[] head = new byte[] { (byte) 0x81, 0x0A };
				byte[] head = new byte[] { (byte) 0x81, len };
				byte[] body = send.getBytes(Charset.forName("UTF-8"));
				byte[] message = new byte[head.length+body.length];
				System.arraycopy(head, 0, message, 0, head.length);
				System.arraycopy(body, 0, message, head.length, body.length);
				out.write(message);
				out.flush();
			}
			// DataInputStream data = new
			// DataInputStream(client.getInputStream());
			// System.out.println(data.read(b));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
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
