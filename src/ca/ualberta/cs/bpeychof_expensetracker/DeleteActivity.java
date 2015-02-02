package ca.ualberta.cs.bpeychof_expensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DeleteActivity extends Activity
{

	Button deleteBT;
	Button editBT;
	int indexofClaim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_info_popup);
	
	
		
		editBT = (Button) findViewById(R.id.claimInfoEditClaimBT);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete, menu);
		return true;
	}

	
	
	public void onStart(){
		super.onStart();
		
		if((getIntent().getExtras()) != null && getIntent().getExtras().containsKey("ClaimIndexToDelete")){
			
			//indexofClaim = (int) getIntent().getSerializableExtra("ClaimIndexToDelete");
			
			
			
			
			
		}
		
		
		deleteBT = (Button)findViewById(R.id.claimInfoDeleteBT);
		deleteBT.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				// TODO Auto-generated method stub
				// TODO startnew intent back to main and delete 
				
			}
		});
		
		
		
	}
	
}
