package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi_interface.Methods;

public class Client {
	
	public static void main(String args[]) throws RemoteException, NotBoundException, Exception{
		Registry registry = LocateRegistry.getRegistry(2964);
		
		Methods mtlServer = (Methods) registry.lookup("MTL");
		Methods lvlServer = (Methods) registry.lookup("LVL");
		Methods ddoServer = (Methods) registry.lookup("DDO");
		
		mtlServer.SayHello();
		lvlServer.SayHello();
		ddoServer.SayHello();
		
		
		
		DatagramSocket dSocket = null;
		try{
		dSocket = new DatagramSocket(2965);
		byte [] m = mtlServer.getRecord().getBytes();
		//int n = mtlServer.getRecord();
		InetAddress dHost =  InetAddress.getByName("localHost");
		
		int serverPort =2965;// dSocket.getPort();//2964;
		
		DatagramPacket request = new DatagramPacket(m, m.length,
				dHost,serverPort);
		dSocket.send(request);
		//InetAddress dHost = request.getAddress();
		byte[] buffer = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
		dSocket.receive(reply);
		  String str = new String(reply.getData(), 0, reply.getLength());  
		    System.out.println(str);  
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("Socket :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Socket :" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(dSocket != null)
				dSocket.close();
		}
		
		
	}

}
