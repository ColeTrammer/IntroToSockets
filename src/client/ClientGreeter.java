package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientGreeter {

   public static void main(String [] args) {
	  //1. Create a String for the ip address of the server. 
	  // If you don't know how to find a computer's ip address, ask about ifconfig on linux/mac and ipconfig on windows.
      
	   Scanner in = new Scanner(System.in);
	   System.out.print("Ip Address: ");
	   String ip = in.nextLine();
	   System.out.print("Port: ");
	   int port = in.nextInt();
	   in.close();
	   
	   try {
		   Socket socket = new Socket(ip, port);
		   DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		   output.writeUTF("Thought provoking message: ");
		   DataInputStream input = new DataInputStream(socket.getInputStream());
		   System.out.printf("Recieved: %n%s%n", input.readUTF());
		   socket.close();
	   } catch (IOException e) {
		   System.err.println("Failed to connect.");
	   }
	   
      //2. Create an integer for the server's port number
      
      //3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
    
    	 //4. Create an object of the Socket class. When initializing the object, pass in the ip address and the port number
 
         //5. Create a DataOutputStream object. When initializing it, use the Server object you created in step 4 to call the getOutputStream() method.
         
         //6. Use the DataOutputStream object to send a message to the server using the writeUTF(String message) method
         
         //7. Create a DataInputStream object. When initializing it, use the Server object you created in step 4 to call the getInputStream() method.
         
         //8. Use the DataInputStream object to print a message from the server using the readUTF() method.
         
         //9. Close the client's server object

   }
}
