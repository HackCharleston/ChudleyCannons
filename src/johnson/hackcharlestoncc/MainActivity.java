package johnson.hackcharlestoncc;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.Build;

import com.google.android.maps.MapActivity;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;



public class MainActivity extends MapActivity {
	TextView descriptionTextView;
	RatingBar itemRatingBar; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
			
			itemRatingBar = (RatingBar) findViewById(R.id.itemRatingBar);
			descriptionTextView = (TextView) findViewById(R.id.descripTextView);
		
			
		
	        // Get a handle to the Map Fragment
	        GoogleMap map = ((MapFragment) getFragmentManager()
	                .findFragmentById(R.id.MapViewLayout)).getMap();
	        
	        
	        Bundle extras = getIntent().getExtras();
	        String [] data = extras.getStringArray("DATA");
	        
	        if(data[0].equals("Angel Oak Park"))
	        {
	        	itemRatingBar.setRating(5);
	        }
	        else if(data[0].equals("College of Charleston Sailing Center"))
	        {
	        	itemRatingBar.setRating(4);
	        }
	        else if(data[0].equals("Magnolia Plantations and Gardens"))
	        {
	        	itemRatingBar.setRating(3);
	        }
	        else if(data[0].equals("James Island County Park"))
	        {
	        	itemRatingBar.setRating(4);
	        }
	        
	        
	        double lat = Double.parseDouble(data[2]);
	        double lon = Double.parseDouble(data[3]);
	        

	        LatLng charleston = new LatLng(lat, lon);

	        map.setMyLocationEnabled(true);
	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(charleston, 13));
	        
	        descriptionTextView.setText(data[1]);

	        map.addMarker(new MarkerOptions()
	                .title(data[0])
	                .position(charleston));

//		}
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}



}

