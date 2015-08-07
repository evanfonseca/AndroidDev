package cvturismo.definicoes;







import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import br.exemplosocialoauth.R;

public class InfLocation extends Activity{

	// GPS Location
		GPSTracker gps;
		// Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();
	
	 @Override 
	    protected void onCreate(Bundle savedInstanceState) {  
			super.onCreate(savedInstanceState);
			setContentView(R.layout.list_row_item); 
			
			
		
		
		// creating GPS Class object
				gps = new GPSTracker(this);

				// check if GPS location can get
				if (gps.canGetLocation()) {
					Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
				} else {
					// Can't get user's current location
					alert.showAlertDialog(InfLocation.this, "GPS Status",
							"Couldn't get location information. Please enable GPS",
							false);
					// stop executing code by return
					return;
				}
				
				
				
		
	 }

	 
	

		

}
