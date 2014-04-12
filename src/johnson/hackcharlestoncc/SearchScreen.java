package johnson.hackcharlestoncc;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;



public class SearchScreen extends Activity {
	
	Button searchButton;
	EditText searchBarEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_screen);

			
			
			searchBarEditText = (EditText) findViewById(R.id.searchBarEditText);
			searchButton = (Button) findViewById(R.id.searchButton);
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			
			searchButton.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
					String keyword = searchBarEditText.getText().toString();
					
					Intent viewContactActivity = new Intent(SearchScreen.this, MainActivity.class);
					
					String [] [] DATA2D = searchResults(keyword, DATA) ;
					
					
					String [] data = new String [4];
					
					if(DATA2D != null)
					{
						
						data[0] = DATA2D[1][0]; // Title
						data[1] = DATA2D[1][1]; // Description 
						data[2] = DATA2D[2][0]; // LAT
						data[3] = DATA2D[2][1]; // LONG
					}
					else
					{
						data[0] = "Angel Oak Park";
						data[1] = "This is the location of Charleston's Angel Oak Tree. It is a " +
								"must-see in the Low Country and is beloved by both locals and " +
								"tourists alike.";
						data[2] = "32.717378";
						data[3] = "-80.080924";
					}
					
					
					
					viewContactActivity.putExtra("DATA", data);
					
					

					startActivity(viewContactActivity);
				}	
			});
			
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static String [][] searchResults(String search, String [][][] searchArray){
		//could use the .length function here instead
		String tempString = "";
		int pageNum = -1;
		for(int i = 0; i < 3; i++){
			tempString = searchArray[i][0][0];
			if(tempString.equals(search)){
				pageNum = i+1;
			}
		}
		String [][] returnArray = null;
		
		if(pageNum != -1)
		{
			returnArray = new String [3][3];
			for(int i = 0; i < 3; i++){
				for(int x = 0; x < 3; x++){
					returnArray[i][x] = searchArray[pageNum-1][i][x];
				}
			}
		}
		
		
		
		
		
		if(pageNum == -1){
			returnArray = null;
		}
		
		return returnArray;
		
	}

	
	
	
	
final String [][][] DATA = {{{"boating","search term", "no website"},
	{"College of Charleston Sailing Center","Sailboat rentals, classes, racing, and other activities available to alumni and the public.", "http://sailing.cofc.edu/"}, 
	{"32.786622", "-79.905116","http://www.charlestonwatertaxi.com/"},
	{"HydroFly Watersports", "Offers harbor tours, flyboarding, fishing charters, jet ski rentals, paddleboarding, kayaking, and more!", "http://www.hydroflynow.com/charleston-watersports"}
	},
	
	{{"bird watching", "search term", "no website"},
	{"Magnolia Plantations and Gardens", "Various tours and a bird watching observation tower for visitors.  A beautiful property with significant amounts of wildlife.","http://www.magnoliaplantation.com/"},
	{"32.877520", "-80.091041", "http://www.cityoffollybeach.com/"},
	{"Pitt Street Bridge", "This old bridge in Mt. Pleasant is now only for pedestrians and is a great place to see wading birds in the low country.","http://www.discoversouthcarolina.com/Insider/outdoor/Blog/10074"}
	},

	{{"bike trails","search term", "no website"},
	{"James Island County Park","There are a variety of activities available to visitors here including bike trails.  They also offer bike rentals for visitors who do not choose to bring their own bike.","http://www.ccprc.com/index.aspx?nid=68"},
	{"32.735504", "-79.983510","http://www.southcarolinaparks.com/ctl/introduction.aspx"},
	{"Marrington Plantation Bike Trail","The main trail is about 14 miles with other optional trails available to explore.  Mountain bikes are recommended for the area, but are not necessary.","http://alltrails.com/trail/us/south-carolina/marrington-plantation"}
}
};
}
