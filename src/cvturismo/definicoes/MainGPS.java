package cvturismo.definicoes;

import java.util.ArrayList;
import java.util.HashMap;


import br.exemplosocialoauth.R;





import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainGPS extends Activity {

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	// Connection detector class
	ConnectionDetector cd;
	// Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();
	
	// GPS Location
	GPSTracker gps;

	// Button
	Button btnShowOnMap;

	// Progress dialog
	ProgressDialog pDialog;
	
	
	// ListItems data
	ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String,String>>();
	
	
	// KEY Strings
	public static String KEY_REFERENCE = "reference"; // id of the place
	public static String KEY_NAME = "name"; // name of the place
	public static String KEY_VICINITY = "vicinity"; // Place area name

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modogps);
		
		
	

		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			// Internet Connection is not present
			alert.showAlertDialog(MainGPS.this, "Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// creating GPS Class object
		gps = new GPSTracker(this);

		// check if GPS location can get
		if (gps.canGetLocation()) {
			Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
		

		
		} else {
			// Can't get user's current location
			alert.showAlertDialog(MainGPS.this, "GPS Status",
					"Couldn't get location information. Please enable GPS",
					false);
			// stop executing code by return
			return;
		}

		
	
}
	
}
