package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.Spring;

import record.Record;
import rmi_interface.Methods;

public class Server extends UnicastRemoteObject implements Methods , Runnable {
	private ArrayList<Record> myArrayList;
	private HashMap<Character,ArrayList<Record>>hashM=new HashMap<Character,ArrayList<Record>>();
	private String name;
	public String getName() {
		return name;
	}

	public int getPort_no() {
		return port_no;
	}


	private int port_no;

	public Server(String name, int port_no) throws RemoteException{
		super();
		this.name = name;
		this.port_no = port_no;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("We are here using object" + this.name);
		DatagramSocket dSocket = null;
		try {
			dSocket = new DatagramSocket(this.port_no);
			byte [] buffer = new byte[1000];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				dSocket.receive(request);
				
				String n = (String)this.getRecord();
				buffer = n.getBytes();
				
				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
						request.getAddress(),request.getPort());
				dSocket.send(reply);
			}
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

	@Override
	public void SayHello() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Hello!!!");
		
	}
	@Override
	public void createTRecord(String firstName, String lastName, String address, int phone, String specialization,
			String location) throws RemoteException {
		Record rec1 = new Record(firstName,lastName,address,phone,specialization,location);
		myArrayList.add(rec1);
		hashM.put(lastName.charAt(0), myArrayList);
	}
	@Override
	public void createSRecord(String firstName, String lastName, String courseRegistered, String status,
			Date statusDate) throws RemoteException {
		// TODO Auto-generated method stub
		Record rec1 = new Record(firstName,lastName, courseRegistered,status,statusDate);
		myArrayList.add(rec1);
		hashM.put(lastName.charAt(0), myArrayList);
	}
	@Override
	public String getRecord()throws RemoteException{
		return " "+ hashM.size();
	}
	@Override
	public void editRecord(String recordID, String fieldName, String newValue) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void editRecord(String recordID, String fieldName, int newValue) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String args[]) throws RemoteException, AlreadyBoundException{
		Server mtlServer = new Server("Montreal", 7788);
		Server lvlServer = new Server("Laval", 7789);
		Server ddoServer = new Server("DDO", 7790);
		
		Registry registry = LocateRegistry.createRegistry(2964);
		
		registry.bind("MTL", mtlServer);
		registry.bind("LVL", lvlServer);
		registry.bind("DDO", ddoServer);
		
		
		Thread t1 = new Thread(mtlServer);
		Thread t2 = new Thread(lvlServer);
		Thread t3 = new Thread(ddoServer);
		
		t1.start();
		t2.start();
		t3.start();
		System.out.println("server is started");
	}

}
