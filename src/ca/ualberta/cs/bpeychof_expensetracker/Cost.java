package ca.ualberta.cs.bpeychof_expensetracker;

import java.io.Serializable;


public class Cost implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8000070779026814675L;
	private double cost;
	private String currency_type;
	
	
	public Cost(double cost , String currency_type){ //constructor
		
		this.cost = cost;
		this.currency_type = currency_type;
		
		
	}
	
	public void setCost( double the_cost){
		
		cost = the_cost;
		
		
	}
	
	public double getCost(){
		
		return cost;
		
		
	}
	
	public void setCurrencyType(String Currency){
		
		currency_type=Currency;
		
		
	}
	
	
	public String getCurrencyType(){
		
		return currency_type;
		
		
		
		
	}
	
	
}
