package ca.ualberta.cs.bpeychof_expensetracker;


public class Status
{
	private String status;
	
	
	
	public Status( String status_name){ //constructor
		
		setStatus(status_name);
		
		
	}
	
	public void setStatus(String status_name){
		
		status = status_name;
			
	}
	
	public String getStatusName(){
		
		
		return status;
		
		
		
	}
	
	
	
	
	
	
}
