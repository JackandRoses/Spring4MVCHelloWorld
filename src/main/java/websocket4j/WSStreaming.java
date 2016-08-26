package websocket4j;

/**
 * This file is part of GNU WebSocket4J.
 * Copyright (C) 2010  Marek Aaron Sapota
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your choice,
 * provided that you also meet, for each linked independent module, the terms and
 * conditions of the license of that module. An independent module is a module
 * which is not derived from or based on this library. If you modify this library,
 * you may extend this exception to your version of the library, but you are not
 * obligated to do so. If you do not wish to do so, delete this exception
 * statement from your version.
 */

import java.io.IOException;
import java.util.ArrayList;

import websocket4j.server.WebServerSocket;
import websocket4j.server.WebSocket;

/**
 * Example presenting an echo server using WebSocket4j.
 */
public class WSStreaming extends Thread {

	public static ArrayList<WebSocket> clients = new ArrayList<WebSocket>();

	public WSStreaming() {}

	private void handleConnection(){
		long nb = 0;
		while (true) {
			if(clients.size() != 0)
			{
				for(int i = 0; i < clients.size(); i++)
				{
					try{
						clients.get(i).sendMessage("message: " + nb);
					}
					catch(IOException e)
					{
						clients.remove(clients.get(i));
					}
				}
				nb++;
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void run() {
		handleConnection();
	}

	public static void main(String[] args) throws IOException {
		int port = 8888;
		boolean first_client = true;
		WebServerSocket socket = new WebServerSocket(port);
		while (true) {
			System.out.println("Streaming server ready. Listen on: " + port + " &&& nb_clients: " + clients.size());
			WebSocket ws = socket.accept();
			if (ws.getRequestUri().equals("/streaming"))
			{
				if(first_client)
				{
					new WSStreaming().start();
					first_client = false;
				}
				clients.add(ws);
			}
			else {
				System.out.println("Unsupported Request-URI");
				try {
					ws.close();
				} catch (IOException e) {
				}
			}
		}

	}

}
