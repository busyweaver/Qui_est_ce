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



public class ThreadReception implements Runnable {

	private BufferedReader in;
	private Thread thread_1;
			public ThreadReception(BufferedReader x)
			{
				in=x;
				thread_1=new Thread(this);
				thread_1.start();
			}
			public void run()
			{
				while(true)
				{

					try{System.out.println(in.readLine());}		catch (IOException e){ }
				}
			}
}
