package com.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private static ServerSocket serverSocket;
	private static final int PORT = 9889;
	private Socket client;
	static private Scanner input;
	static private PrintWriter output;

	public static void main(String[] args) throws IOException {
		String received;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Waiting");
			Socket client = serverSocket.accept();
			System.out.println("Connected");
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);

			do {
				// Accept message from client on the socket's input stream…
				received = input.nextLine();
				System.out.println("Received:" + received);

				// Echo message back to client on the socket's output stream…
				output.println("ECHO: " + received);
				
			} while (!received.equals("QUIT"));

		} catch (IOException ioEx) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}
	}
}