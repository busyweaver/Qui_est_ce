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



public class Serveur 
{
	private ServerSocket socket_serveur  ;
  	private Vector<ThreadClient> clientsChat; 
  	private Vector<ThreadClient> clientsJouer; 
	private int nbClientsChat;
	private int nbClientsJouer;
	private List salons;

	public Serveur()
	{

		clientsChat = new Vector<ThreadClient>();
		clientsJouer = new Vector<ThreadClient>();
		nbClientsChat=0;
		nbClientsJouer=0;
		try {

			socket_serveur = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute du port ... "+socket_serveur.getLocalPort());

			while (true) // attente en boucle de connexion (bloquant sur ss.accept)
			        new ThreadClient(socket_serveur.accept(),this); // un client se connecte, un nouveau thread 
		}
		 catch (Exception e) { }
	}

	synchronized public void sendMessage(int numClient,String message,List salon)
	{
		
		
		PrintWriter out;  
		String envoi="";
		for(int i=0;i<salon.size();i++) 
		{
			envoi = numClient+"\n"+message;
			ThreadClient tc=this.clientsChat.elementAt(i);
			tc.getOut().println(envoi);
			tc.getOut().flush();
			

		}	
	}
	
	synchronized public int addClient(ThreadClient x,int option)
	{
		int retour;
		System.out.println("addclients");
		if(option==1)
		{
			nbClientsChat++;
			clientsChat.addElement(x);
			retour=clientsChat.size()-1;
		}
		else
		{
			nbClientsJouer++;
			clientsJouer.addElement(x);
			retour=clientsJouer.size()-1;
		}	

		return retour; 
	}
		      
	public int getNbClientsChat()
	{
		return nbClientsChat;
	}

	public int getNbClientsJouer()
	{
		return nbClientsJouer;
	}

	synchronized public String getClients(int sauf,int option)
	{
		String message="";
		if(option==1)
		{
			for(int i=0;i<getNbClientsChat();i++)
			{
				if(i!=sauf)
				{
				message=message+"client "+i+"   ";
				System.out.println(message);
				}
			}
		}
		else
		{
			for(int i=0;i<getNbClientsJouer();i++)
			{
				if(i!=sauf)
				{
				message=message+"client "+i+"   ";
				System.out.println(message);
				}
			}
			
		}	
		return message; 

	}		

	
}
