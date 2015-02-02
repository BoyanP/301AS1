package ca.ualberta.cs.bpeychof_expensetracker;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class ClaimList implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2881338136448009868L;
	private ArrayList<ClaimItem> Claim_List;


	
	public ClaimList(){
		
		Claim_List = new ArrayList<ClaimItem>();
		
		
		
	}
	
	
	public Collection<ClaimItem> getClaimList(){
		
		
		return Claim_List;
		
	}
	
	public void addClaim(ClaimItem Claim){
		
		Claim_List.add(Claim);
		
	}
	
	//got this from LonelyTwitter

	
	
	
}
