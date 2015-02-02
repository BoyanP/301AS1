package ca.ualberta.cs.bpeychof_expensetracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ClaimItem implements TrackerItem, Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2980617206760527885L;
	private String claim_name;
	private ArrayList<ExpenseItem> expenses_in_claim; ;
	private Status status_of_claim;
	private String claim_description;
	private Date start_date;
	private Date end_date;
	//private ArrayList<Cost> total_cost; // should hold doubles and currency
	
	
	
	
	
	public ClaimItem(String name){ // constructor
		
		setName(name);
		expenses_in_claim = new ArrayList<ExpenseItem>();
		//total_cost = new ArrayList<Cost>();
		
	}
	
	
	@Override
	public void setName(String name)
	{

		// TODO Auto-generated method stub
		
		claim_name = name;
		
	}

	@Override
	public String getName()
	{

		// TODO Auto-generated method stub
		return claim_name;
	}

	
	public ArrayList<String> getTotalCost(){
		
		//TODO 
		return null;

		
		
	}
	
	public void setStatus(Status Status){
		
		status_of_claim = Status;
		
		
	}
	
	
	public Status getStatus(){
		
		return status_of_claim;
		
	}
	

	
	public void setStartDate(Date date){
		
		start_date = date;
		
		
	}
	
	public Date getStartDate(){
		
		return start_date;
		
	}
	
	public void setEndDate(Date date){
		
		end_date = date;
		
	}
	
	public Date getEndDate(){
		
		return end_date;
	}
	
	public void setClaimDesc(String Description){
		
		claim_description=Description;	
		
		
	}
	
	public String getClaimDesc(){
		
		return claim_description;
		
	}
	
	/*public String totalCost(){
		//TODO
		String TotalCost;
		
		for(int i = 0; i < expenses_in_claim.size();i++){
			
			
			
			
		}
		
		return null;
	}*/
	
	public ArrayList<ExpenseItem> getExpenses(){
		
		return expenses_in_claim;
		
	}
	
	public void setExpenseList(ArrayList<ExpenseItem> Expenses){
		
		expenses_in_claim = Expenses;
		
		
	}
	
	public void AddExpense(ExpenseItem Expense){
		
		expenses_in_claim.add(Expense);
		
	}
	
	public void InsertExpense(ExpenseItem Expense, int index){
		
		expenses_in_claim.set(index, Expense);
		
	}
	
	public int getExpenseIndex(ExpenseItem Expense){
		
		return expenses_in_claim.indexOf(Expense);
		
		
	}
	
	@Override
	public String toString(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Startdate = formatter.format(this.getStartDate());
		
		String Enddate = formatter.format(this.getEndDate());
		
		
		return"Claim Name:" + this.getName() +"\n" + "from: " + Startdate 
				+ "until: " + Enddate + "\n Desc: " + this.getClaimDesc()
				+"\n #ofExpenses: " + expenses_in_claim.size();
		
	}
}
