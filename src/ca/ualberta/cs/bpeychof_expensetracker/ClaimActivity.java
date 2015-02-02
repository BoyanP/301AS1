package ca.ualberta.cs.bpeychof_expensetracker;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ClaimActivity extends Activity
{

	ClaimList ClaimList= null;
	int ExpenseBTUse = 0;
	ClaimItem Claim;
	//private static final String SAVEFILE  = "file.sav";
	private ListView ExpenseListView;
	private EditText ClaimNameET;
	private EditText ClaimDescET;
	private EditText StartDateET;
	private EditText EndDateET;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_claim);
		
		//ClaimItem Claim; USE THE EXTRA DATA THING FROM INTENT TO KEEP THAT. 
		Log.i("HELLOclaim","OnCreate");
		//ClaimList = new ClaimList();
		ExpenseListView = (ListView) findViewById(R.id.ExpenseListView);
		ExpenseListView.setClickable(false);
		ClaimNameET = (EditText) findViewById(R.id.editClaimNameET);
		ClaimDescET = (EditText) findViewById(R.id.ClaimDescET);
		StartDateET = (EditText) findViewById(R.id.StartDateET);
		EndDateET = (EditText) findViewById(R.id.EndDateET);
		
		
		Claim =new ClaimItem("");
		//Button SubmitBT = (Button) findViewById(R.id.SubmitClaimBT);
		Button AddExpenseBT = (Button) findViewById(R.id.AddExpenseBT);
		AddExpenseBT.setOnClickListener(new View.OnClickListener()
	
		{
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v)
			{
			
				// TODO Auto-generated method stub
				String ClaimTitle = ClaimNameET.getText().toString();
				String ClaimDesc = ClaimDescET.getText().toString();
				
				//Get Date
				//with help from: http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
				String StartDate  = StartDateET.getText().toString();
				DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
				Date sdate=null;
				Date edate=null;
				try{ 
					sdate = format.parse(StartDate);
					Claim.setStartDate(sdate);
				}catch(ParseException e){
					
					Toast.makeText(ClaimActivity.this,"You did not Fill in Start date properly", Toast.LENGTH_SHORT).show();
					
				}
				
				String EndDate = EndDateET.getText().toString();
				try{
					
					edate = format.parse(EndDate);
					Claim.setEndDate(edate);
					
				}catch(ParseException e){
					
					Toast.makeText(ClaimActivity.this,"You did not Fill in End date properly", Toast.LENGTH_SHORT).show();
					
				}
				
				
				if(!(ClaimTitle.isEmpty())){
					Claim.setName(ClaimTitle);
					}
				if(!(ClaimDesc.isEmpty())){
					Claim.setClaimDesc(ClaimDesc);
					}
				if(!(StartDate.isEmpty())){
					Claim.setStartDate(sdate);
					}
				if(!(EndDate.isEmpty())){
					Claim.setEndDate(edate);
					}
					
					
					
				if(ClaimTitle.isEmpty()||ClaimDesc.isEmpty()||StartDate.isEmpty()||EndDate.isEmpty()){// need expenses too
					
					Toast.makeText(ClaimActivity.this,"Please enter a name and Description and Dates", Toast.LENGTH_SHORT).show();
					
					
				}else{
				Log.i("Hello666","Do i die!?");	
				//ClaimList.addClaim(Claim);
				
				Intent AddExpenseIntent = new Intent(ClaimActivity.this , ExpenseActivity.class);
				//AddExpenseIntent.putExtra("ClaimExtra", Claim);
				startActivity(AddExpenseIntent);
				}
				
				
			}
		});
		
		
		
		
		
		
	
		
	}
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim, menu);
		return true;
	}

	
	
	@SuppressWarnings("unchecked")
	protected void onStart(){
		super.onStart();
		
		Button FinishBT = (Button) findViewById(R.id.saveEditsButton);
		FinishBT.setOnClickListener(new View.OnClickListener()
		{
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v)
			{
			
				// TODO Auto-generated method stub
				String ClaimTitle = ClaimNameET.getText().toString();
				String ClaimDesc = ClaimDescET.getText().toString();
				
				
				//Get Date
				//with help from: http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
				String StartDate  = StartDateET.getText().toString();
				DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
				Date sdate;
				Date edate;
				try{ 
					sdate = format.parse(StartDate);
					Claim.setStartDate(sdate);
				}catch(ParseException e){
					
					Toast.makeText(ClaimActivity.this,"You did not Fill in Start date properly", Toast.LENGTH_SHORT).show();
					
				}
				
				String EndDate = EndDateET.getText().toString();
				try{
					
					edate = format.parse(EndDate);
					Claim.setEndDate(edate);
					
				}catch(ParseException e){
					
					Toast.makeText(ClaimActivity.this,"You did not Fill in End date properly", Toast.LENGTH_SHORT).show();
					
				}
				
				
				if(ClaimTitle.isEmpty()||ClaimDesc.isEmpty()||StartDate.isEmpty()||EndDate.isEmpty()){// need expenses too
					
					Toast.makeText(ClaimActivity.this,"Please enter a name and Description and Dates", Toast.LENGTH_SHORT).show();
					
					
				}else{
					
					Claim.setName(ClaimTitle);
					Claim.setClaimDesc(ClaimDesc);
					Log.i("HELLO111", "howdy");
					
	
					//if((ClaimList.getClaimList().contains(Claim))==false){
					ClaimList.addClaim(Claim);
					//}
					Log.i("HELLO33","Have Mercy");
					Intent BackToMain = new Intent(ClaimActivity.this,MainActivity.class);
					BackToMain.putExtra("ClaimListExtra", ClaimList);
					startActivity(BackToMain);
					//setResult(RESULT_OK,BackToMain);
				
				}
				
				
				
			}
		});
		
		
		

		if((getIntent().getExtras()) != null && getIntent().getExtras().containsKey("ClaimListEdit")){
		
			ClaimItem edit_Claim = (ClaimItem) getIntent().getSerializableExtra("ClaimListEdit"); 
			boolean yes_no = ClaimList.equals(edit_Claim); 
			if(yes_no==true){
				
				
				
				
			} 
			
		
	
		}
		
		
		
	
		
		
		
		
		if((getIntent().getExtras()) != null && getIntent().getExtras().containsKey("ClaimListExtraToClaim")){
			Log.i("HELLO99","PLEASE");
			ClaimList = (ClaimList) getIntent().getSerializableExtra("ClaimListExtraToClaim");
			Log.i("HELLO73",""+ClaimList.getClaimList().size());
		
			boolean plz = ClaimList.getClaimList().contains(Claim);
			
			if(!plz){
				
				Log.i("HELLOinplz," , "Confusing");
				
				//ClaimItem Claim_1 = ClaimList.getClaimList().
				
			}
		}
		
		if ((getIntent().getExtras())!=null && getIntent().getExtras().containsKey("ClaimExtraBack")){
			

			
			ArrayList<ExpenseItem> Expenses = (ArrayList<ExpenseItem>) getIntent().getSerializableExtra("ClaimExtraBack");
			
			Claim.setExpenseList(Expenses);
			Log.i("HELLO1",""+Claim.getExpenses().size());
			String ClaimName = Claim.getName();
			String ClaimDesc =Claim.getClaimDesc();
			ClaimNameET.setText(ClaimName);
			ClaimDescET.setText(ClaimDesc);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String Startdate = formatter.format(Claim.getStartDate());
			
			String Enddate = formatter.format(Claim.getEndDate());
			
			StartDateET.setText(Startdate);
			EndDateET.setText(Enddate);
			
			//ClaimList.getClaimList().remove(Claim);
		}
		
		//LonelyTwitter
		ArrayList<ExpenseItem>  ExpenseList = new ArrayList<ExpenseItem>();
		ExpenseList= Claim.getExpenses();
		Log.d("HELLO", ""+ ExpenseList.size());
		ArrayAdapter<ExpenseItem> expense_adapter = new ArrayAdapter<ExpenseItem>(this,R.layout.expense_tv,ExpenseList);
		ExpenseListView.setAdapter(expense_adapter);
		Log.i("HELLO666","Do i die");	
		
		
		
	}//onStart
	
	
	
	/*private void saveClaimList(){
		Gson gson = new Gson();	
			try{
				FileOutputStream fos = openFileOutput(SAVEFILE,0);
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				Log.i("SavedClaim","claimListsize"+ClaimList.getClaimList().size());
				gson.toJson(ClaimList,osw);
				osw.flush();
				fos.close();
			} catch (FileNotFoundException e){
				
				e.printStackTrace();
			}catch (IOException e){
				
				e.printStackTrace();
			}
			
			
		}*/
	
	
}
