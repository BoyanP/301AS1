package ca.ualberta.cs.bpeychof_expensetracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Adapter;

public class MainActivity extends Activity
{
	//private Button claim_button;

	ClaimList ClaimList;
	private ListView ClaimsLV;
	private Button EmailBT;
	final static String SAVEFILE = "file.sav";	
	String emailString="";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("ONCREATE","poo");
		
		ClaimsLV = (ListView) findViewById(R.id.ClaimLV);
		ClaimList= new ClaimList();
		
		//ClaimItem testClaim = new ClaimItem("Fly to L.A");
	
		
		//ClaimList.addClaim(testClaim);
	
		
		Button claim_button = (Button) findViewById(R.id.claimButton);
		claim_button.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				//from http://stackoverflow.com/questions/4186021/how-to-start-new-activity-on-button-click
				Intent EditClaimIntent = new Intent(MainActivity.this , ClaimActivity.class);
				saveClaimList();
				EditClaimIntent.putExtra("ClaimListExtraToClaim", ClaimList);
				
				startActivity(EditClaimIntent);
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this,"Please enter an email address first", Toast.LENGTH_SHORT).show();
				// TODO make new activity to implement
				
			}
		});
		
		
		
		
		
		
		
		
		
	}//on start

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onStart(){
		
		super.onStart();
		//from http://stackoverflow.com/questions/18441846/how-sort-a-arraylist-in-java
		ClaimList = loadClaimList();
		Collections.sort((ArrayList<ClaimItem>)ClaimList.getClaimList(),new Comparator<ClaimItem>(){
			
			@Override
			public int compare(ClaimItem Claim1, ClaimItem Claim2){
				
				
				return Claim1.getStartDate().compareTo(Claim2.getStartDate());
				
			}
			
			
		});
		
		
		
		//from http://stackoverflow.com/questions/14597168/android-listview-object-not-liking-new-onitemclicklistener-as-argument-to-seto
		
		ClaimsLV.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			
			@Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	          
				//Intent ClaimsPopup = new Intent();
				
				Intent EditClaims = new Intent(getApplicationContext(), ClaimActivity.class);
				EditClaims.putExtra("ClaimListEdit",((ArrayList<ClaimItem>) ClaimList.getClaimList()).get(position));
				
		        startActivity(EditClaims);
	        }
	    });
		
		
		ClaimsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			
			@Override
		    public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id) {
		          
					//Intent ClaimsPopup = new Intent();
			//	ClaimList.getClaimList().remove(position);
				//ArrayList<ClaimItem> Claim_List = new ArrayList<ClaimItem>();
			//	Claim_List = (ArrayList<ClaimItem>) ClaimList.getClaimList();
			//	ArrayAdapter<ClaimItem> adapter = new ArrayAdapter<ClaimItem>(this,R.layout.claim_tv,Claim_List);
		
				
				//ClaimsLV.setAdapter(adapter);
				
				
			//	saveClaimList();
				return true;
			}
		 });
			
		
		
		if((getIntent().getExtras()) != null && getIntent().getExtras().containsKey("ClaimListExtra")){
			Log.i("HELLO3","Do i comehere");
			ClaimList = (ClaimList) getIntent().getSerializableExtra("ClaimListExtra");
			Log.i("HELLOplz","i cri");
			saveClaimList();
			
			
			
			
		}
		
		Log.i("HELLOmain","CL SIZE"+ClaimList.getClaimList().size());
		ArrayList<ClaimItem> Claim_List = new ArrayList<ClaimItem>();
		Claim_List = (ArrayList<ClaimItem>) ClaimList.getClaimList();
		ArrayAdapter<ClaimItem> Claim_adapter = new ArrayAdapter<ClaimItem>(this,R.layout.claim_tv,Claim_List);
		ClaimsLV.setAdapter(Claim_adapter);
	
		for(ClaimItem c: ClaimList.getClaimList()){
			emailString+=c.toString();
		}
		
		
		//TODO check for extras here
		
		//TODO
				// load claims from file 
				//maybe make a class that can handle this
				
		EmailBT = (Button)findViewById(R.id.emailBT);
		EmailBT.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				// TODO Auto-generated method stub
				
				Intent i = new Intent(Intent.ACTION_SEND);
		        i.setType("text/plain");
		        i.putExtra(Intent.EXTRA_EMAIL,
		                        new String[] { "" });
		        i.putExtra(Intent.EXTRA_SUBJECT,"Expense Claims:");
		        i.putExtra(Intent.EXTRA_TEXT,emailString );
		        try {
		            startActivity(Intent.createChooser(i, "Send mail..."));
		        } catch (android.content.ActivityNotFoundException ex) {
		            Toast.makeText(MainActivity.this,
		                            "There are no email clients installed.",
		                            Toast.LENGTH_SHORT).show();
		        }
				
				
				
			}
		});
		
	}
	

	private void saveClaimList(){
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
		
		
	}

	public ClaimList loadClaimList(){
		ClaimList Claim_List = null;
		Gson gson = new Gson();
		try{
			FileInputStream fis = openFileInput(SAVEFILE);
			Type dataType = new TypeToken<ClaimList>() {}.getType();
			InputStreamReader isr = new InputStreamReader(fis);
			Claim_List = gson.fromJson(isr, dataType);
			fis.close();
			
		}catch(FileNotFoundException e){
			
			e.printStackTrace();
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
		if(Claim_List==null){
			Log.i("HELLOmainload", ""+this.ClaimList.getClaimList().size());
			Claim_List = new ClaimList();
			//Log.i("HELLOloadafter",""+ClaimList.getClaimList().size());
		}
		
		return Claim_List;
			
		
		
	}
	
	public void onPause(){
		
		super.onPause();
	//	Log.i("ONPAUSE","poo");
		saveClaimList();
		
	}
	
	
}
