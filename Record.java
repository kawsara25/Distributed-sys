package record;

import java.util.Date;

public class Record {
		
		private String firstName;
		private String lastName;
		private String address;
		private int phone;
		private String specialization;
		private String location;
		private String courseRegistered;
		private String status;
		private	Date statusDate;
		private int recordCount;
		private String recordID = "";
		
		
		public Record(String firstName, String lastName, String address, int phone, String specialization,
				String location)  {
			// TODO Auto-generated method stub
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.phone = phone;
			this.specialization = specialization;
			
			
		}
		public Record(String firstName, String lastName, String courseRegistered, String status,
				Date statusDate)  {
			// TODO Auto-generated method stub
			this.firstName = firstName;
			this.lastName = lastName;
			this.courseRegistered = courseRegistered;
			this.status = status;
			this.statusDate = statusDate;
			
		}
		
}
