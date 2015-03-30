import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ListIterator;





public class Client
{
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket_serveur;
	private ThreadReception reception;


	public Client()
	{
		try 
		{
		
			socket_serveur = new Socket("127.0.0.1",2009);	
		        System.out.println("Demande de connexion");

			out = new PrintWriter(socket_serveur.getOutputStream());
      			in = new BufferedReader(new InputStreamReader(socket_serveur.getInputStream()));
			reception=new ThreadReception(in);
			/* String message_distant = in.readLine();*/
			communication_serveur();
		        socket_serveur.close();
		       
		}
		catch (IOException e){ }
	}

	public void communication_serveur()
	{

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		/*try{System.out.println(in.readLine());}		catch (IOException e){ }
		try{System.out.println(in.readLine());}		catch (IOException e){ }
		try{System.out.println(in.readLine());}		catch (IOException e){ }*/
		String s="";
		try{s = bufferRead.readLine();}
		catch (IOException e){ }

	
		out.println(s);
		out.flush();
		s="";
	/*	try{System.out.println(in.readLine());}		catch (IOException e){ }
		try{System.out.println(in.readLine());}		catch (IOException e){ }*/

		System.out.println("choix du correspondant");
		try{s = bufferRead.readLine();}
		catch (IOException e){ }
		out.println(s);
		out.flush();

		while(true)
		{
			System.out.println("message:");
			try{s = bufferRead.readLine();}
			catch (IOException e){ }
			out.println(s);
			out.flush();

		}
		
		
	}
}
