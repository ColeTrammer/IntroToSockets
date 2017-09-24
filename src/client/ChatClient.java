package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient {

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Ip Address: ");
		String ip = in.nextLine();
		System.out.print("Port: ");
		int port = in.nextInt();
		in.close();
	   
		try {
			Socket connection = new Socket(ip, port);
			System.out.println("Connected...\n");
			
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
			System.err.println("Failed to connect.");
		}
	}
}
