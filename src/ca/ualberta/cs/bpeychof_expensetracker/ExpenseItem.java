package ca.ualberta.cs.bpeychof_expensetracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.renderscript.ProgramFragmentFixedFunction.Builder.Format;


public class ExpenseItem implements TrackerItem, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6184067314000583031L;
	private String expense_name;
	private Cost cost_of_expense = new Cost(0, null);
	private Date date_of_expense;
	private String expense_description;
	
	
	public ExpenseItem(String name){ //constructor
		
		this.setName(name);
		
		
		
	}
	
	@Override
	public void setName(String name)
	{
		expense_name = name;
		
		
		
	}

	@Override
	public String getName()
	{

		return expense_name;
	}

	
	public void setCost(double Cost){
		
		cost_of_expense.setCost(Cost);
		
		
	}
	
	
	public double getCost(){
		
		return cost_of_expense.getCost();
		
		
		
	}
	
	public void SetCurrencyType(String Currency){
		
		cost_of_expense.setCurrencyType(Currency);
		
		
	}
	
	
	public String getCurrencyType(){
		
		return cost_of_expense.getCurrencyType();
		
		
	}
	
	public void setDate(Date date){
		
		date_of_expense = date;
		
		
	}
	
	public Date getDate(){
		
		return date_of_expense;
		
	}
	
	public void setDesc(String Desc){
		
		expense_description = Desc;
		
		
	}
	
	public String getDesc(){
		
		
		return expense_description;
		
	}
	
	//from http://www.ezzylearning.com/tutorial/binding-android-listview-with-custom-objects-using-arrayadapter
	
	@Override
	public String toString(){
		


		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(this.getDate());


		
		return "Expense Name: "+this.expense_name +"\n"+"Cost:" +this.getCost() +" "+ this.getCurrencyType() 
				+"\n" + "Date: " + date + "\n" + "Desc:" + this.getDesc();
		
		
		
	}
	
}
