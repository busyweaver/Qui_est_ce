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
			communication_serveur();
		        socket_serveur.close();
		       
		}
		catch (IOException e){ }
	}

	public void communication_serveur()
	{

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String clavier="";
		try{clavier = bufferRead.readLine();}
		catch (IOException e){ }
		envoie_message_serveur(clavier);/*choix menu*/
		if(clavier.equals("1")==true)
			chat();
		if(clavier.equals("2")==true)
			jouer();

	}
	
	public void jouer()
	{
		
	}


	
	public void chat()
	{

		String clavier;
		clavier=lire_ligne();

		while(clavier.equals("q")==false)
		{
			clavier="";
			clavier=lire_ligne();
			envoie_message_serveur(clavier);/*choix correspondant*/

		}

		clavier="";

		while(clavier.equals("stop")==false)
		{
			clavier="";
			clavier=lire_ligne();
			envoie_message_serveur(clavier);/*message*/


		}
			
	}
	public  String lire_ligne()
	{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String clavier="";
		try{clavier = bufferRead.readLine();}
		catch (IOException e){ }
		return clavier;	
	}
		
	public void envoie_message_serveur(String message)
	{
		out.println(message);
		out.flush();
		
	}










}
