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



public class ThreadClient implements Runnable {
	
	
		
		
		private Socket socket_client;
		private Serveur serveur;
		private int numClientChat;
		private int numClientJouer;
		private BufferedReader in;
		private PrintWriter out;
		private Thread thread_client;




		public ThreadClient(Socket x,Serveur s)
		{
			numClientChat=-1;
			numClientJouer=-1;

			try
			{
			this.socket_client=x;
			this.serveur=s;
			out = new PrintWriter(socket_client.getOutputStream());
      			in = new BufferedReader(new InputStreamReader(socket_client.getInputStream()));


			}
			catch (IOException e){ }
			thread_client=new Thread(this);
			thread_client.start();	
		}

		public void run()
		{
			String message = "";
			try
			{

				while(true)
				{
					out.println("que voulez vous faire?");
					out.println("1. pour chater");
					out.println("2. pour jouer");
					out.flush(); 
					String reponse="";
					reponse=in.readLine();
					/* verifier que veut faire le client*/
					if(reponse.charAt(0)=='1')
					{
						numClientChat=serveur.addClient(this,1);
						chat();
					}
					if(reponse.charAt(0)=='2')
					{
						numClientJouer=serveur.addClient(this,2);
						jouer();
					}
				}
						
						
				


			}
			catch (Exception e){ }
		}


public void chat()
{
	List salon;
	salon=new ArrayList();
	out.println("***********************");	
	out.println("Bienvenue dans le chat");	
	out.println("***********************");	

	try
	{

		envoie_message_client(getListClients(1));
		String reponse="";
		while(reponse.equals("q")==false)
		{
			reponse="";
			reponse=in.readLine();
			salon.add(reponse);

		}
		reponse="";
		while(reponse.equals("stop")==false)
		{
			reponse="";		
			reponse=in.readLine();
			serveur.sendMessage(numClientChat,reponse,salon);	

		}
	}
	catch (Exception e){ }				

}

	

public void jouer()
{

}

public String getListClients(int option)
{
	String clients="***********************\nListe personnes connectees\n***********************\n";
	if(option==1)
		clients=clients+serveur.getClients(numClientChat,1);
	else
		clients=serveur.getClients(numClientJouer,2);
	
	return clients;
}

public void envoie_message_client(String message)
{
	out.println(message);		
	out.flush();		
}

public PrintWriter getOut()
{
	return out;
}

}




















