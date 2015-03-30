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
			out.println("Vous êtes connecté zéro !");
		        out.flush();
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
	out.println("***********************");	
	out.println("Bienvenue dans le chat");	
	out.println("***********************");	
      	numClient = serveur.addClient(this,1);

	try{

	getListClients(1);
	serveur.sendMessage(numClient,
	String reponse="";
	reponse=in.readLine();
	System.out.println(reponse);
	List salon;
	salon=new ArrayList();
	salon.add(numClient);
	salon.add(reponse.charAt(0));
	String message="";

	
	
	while(true)
	{
		message=in.readLine();
		System.out.println("message recu par thread"+message);
		serveur.sendMessage(numClient,message,salon);	
		message="";	
		/*while(in.read(commande, 0, 1)!=-1)
		{
			if (commande[0] != '\u0000' && commande[0] != '\n' && commande[0] != '\r')
				 message += commande[0];
			else
			{
				message=message+'\u0000';
				serveur.sendMessage(message,salon);
			}
		}*/
	}
	}
	catch (Exception e){ }				

}


public PrintWriter getOut()
{
	return out;
}

	

public void jouer()
{
	System.out.println("***********************");	
	System.out.println("Bienvenue dans le jeu");	
	System.out.println("***********************");	
	System.out.println("voici la liste des personnes connectes a qui souhaitez vous parler?");
}

public String getListClients(int option)
{
	
		out.println("***********************");	
		out.println("Liste personnes connectees");	
		out.println("***********************");	
		String clients="";
	if(option==1)
		clients=serveur.getClients(numClientChat,1);
	else
		clients=serveur.getClients(numClientJouer,2);
	out.println(clients);		
	out.flush();
	return clients;
}



}




















