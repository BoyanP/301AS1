package ca.ualberta.cs.bpeychof_expensetracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseActivity extends Activity
{

	
	private ClaimItem Claim;
	private ExpenseItem Expense; 
	private EditText ExpenseName;
	private EditText ExpenseDate;
	private EditText description;
	private Spinner spinner1;
	private EditText cost1;
	private int editFlag;
	private int ExpenseItemIndex;
	final static String SAVEFILE = "file2.sav";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_expense);
		
	
		editFlag = 0;
		final EditText cost2 = (EditText) findViewById(R.id.CDN_ET);
		final EditText cost3 = (EditText) findViewById(R.id.EUR_ET);
		final EditText cost4 = (EditText) findViewById(R.id.GBP_ET);
		
		final Spinner spinner2 = (Spinner) findViewById(R.id.CDN_Spinner);
		final Spinner spinner3 = (Spinner) findViewById(R.id.EUR_Spinner);
		final Spinner spinner4 = (Spinner) findViewById(R.id.GBP_Spinner);
	
		
		
		
	
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}

	
	
	
	public void onStart(){
		super.onStart();
		
		cost1 = (EditText) findViewById(R.id.USD_ET);
		spinner1 = (Spinner) findViewById(R.id.USD_Spinner);
		ExpenseName = (EditText) findViewById(R.id.ExpenseNameET);
		ExpenseDate = (EditText) findViewById(R.id.ExpenseDateET);
		description = (EditText) findViewById(R.id.DescripET);
		final ArrayList<ExpenseItem> ExpenseList = new ArrayList<ExpenseItem>();
		
		//Claim from ClaimActivity
		//Log.i("HELLO","AREwe here");
		Claim = (ClaimItem) getIntent().getSerializableExtra("ClaimExtra");
		
		
		
		
		Button addExpenseBt = (Button) findViewById(R.id.FinishExpenseAddBT);
		addExpenseBt.setOnClickListener(new View.OnClickListener()
		{
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v)
			{
			
				// TODO Auto-generated method stub
				
			
			
				
				//Get Title
				String Expense_title = ExpenseName.getText().toString();
				Expense= new ExpenseItem(Expense_title);
				
				//Get Date
				//with help from: http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
				String Date  = ExpenseDate.getText().toString();
				DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
				try{ 
					Date date = format.parse(Date);
					Expense.setDate(date);
				}catch(ParseException e){
					
					Toast.makeText(ExpenseActivity.this,"You did not Fill in date properly", Toast.LENGTH_SHORT).show();
					
				}
				
				
				//Get description
				String Expense_desc = description.getText().toString();
				Expense.setDesc(Expense_desc);
				
				//Get Cost
				
				String cost_string = cost1.getText().toString();
			
				//from http://stackoverflow.com/questions/15037465/converting-edittext-to-int-android
				if (cost_string.isEmpty()){
					Toast.makeText(ExpenseActivity.this,"Please Fill in All required fields", Toast.LENGTH_SHORT).show();
				
				}
				else{
					if(cost_string!=null){
						double cost = Double.parseDouble(cost_string);
						Expense.setCost(cost);
					}
				}
				
				//from http://stackoverflow.com/questions/10331854/how-to-get-spinner-selected-item-value-to-string
				String Currency = (String) spinner1.getSelectedItem();
		
				Expense.SetCurrencyType(Currency);
				
				if(Expense_title.isEmpty() || Expense_desc.isEmpty()||cost_string.isEmpty()){
					
					Toast.makeText(ExpenseActivity.this,"Please Fill in All required fields", Toast.LENGTH_SHORT).show();
					
				}
				else{
				
					//TODO Go back to previous activity(ClaimActivity) and bring new claim item with you
					Log.i("HELLO51",""+Claim.getName());
					Claim.AddExpense(Expense);
					Log.i("HELLO4",""+Claim.getExpenses().size());
						
					//if(editFlag ==1){
						
					//	Claim.InsertExpense(Expense, index);
						
						
				//	}
					//saveExpenseList();
					Intent BackToClaim = new Intent(ExpenseActivity.this , ClaimActivity.class);
					BackToClaim.putExtra("ClaimExtraBack", Claim.getExpenses());
					Log.i("HELLOexpense",""+Claim.getName());
					
					startActivity(BackToClaim);
				
				}
				
			}
		});
		
		
		
		
		
		
		
		
		
		
	}
	
	/*private void saveExpenseList(){
	Gson gson = new Gson();	
		try{
			FileOutputStream fos = openFileOutput(SAVEFILE,0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(Claim.getExpenses(),osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e){
			
			e.printStackTrace();
		}catch (IOException e){
			
			e.printStackTrace();
		}
		
		
	}*/

	public ArrayList<ExpenseItem> loadExpenseList(){
		ArrayList<ExpenseItem> ExpenseList = null;
		Gson gson = new Gson();
		try{
			FileInputStream fis = openFileInput(SAVEFILE);
			Type dataType = new TypeToken<ArrayList<ExpenseItem>>() {}.getType();
			InputStreamReader isr = new InputStreamReader(fis);
			ExpenseList = gson.fromJson(isr, dataType);
			fis.close();
			
		}catch(FileNotFoundException e){
			
			e.printStackTrace();
		}catch(IOException e){
			
			e.printStackTrace();
		}
		if(ExpenseList==null){
			ExpenseList = new ArrayList<ExpenseItem>();
			
		}
		return ExpenseList;
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}





