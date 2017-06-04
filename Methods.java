package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface Methods extends Remote {
			public void SayHello() throws RemoteException;
			
			public void createTRecord(String firstName,String lastName,String address,int phone,String specialization,String location)throws RemoteException;
			public void createSRecord (String firstName,String lastName,String courseRegistered,String status,Date statusDate)throws RemoteException; 
			public String getRecord()throws RemoteException;
			public void editRecord (String recordID,String fieldName,String newValue)throws RemoteException; 
			public void editRecord (String recordID,String fieldName,int newValue)throws RemoteException;
			
}
