package server;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatServer extends Thread {
	ServerSocket server;

	public ChatServer() throws IOException {
		server = new ServerSocket(8080);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for connection...");
				Socket connection = server.accept();
				System.out.printf("Connected to %s.%n", connection.getInetAddress());
				
				Scanner kbd = new Scanner(System.in);
				ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
				
				while (true) {
					if (kbd.hasNextLine()) {
						String line = kbd.nextLine();
						output.writeObject(line);
						if (line.equals("::Exit")) {
							break;
						}
					}
					if (input.available() > 0) {
						String recieved = input.readUTF();
						System.out.printf("%s: %s%n", connection.getInetAddress(), recieved);
						if (recieved.equals("::Exit")) {
							break;
						}
					}
				}
					
				kbd.close();
				connection.close();
			} catch (IOException e) {
				System.err.println("Connection failed.");
			}
		}
	}

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				new ServerGreeter().run();
			} catch (IOException e) {
				System.err.println("Failed to initialize the server.");
			}
		}).start();
	}
}