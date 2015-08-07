package cvturismo.maps;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import br.exemplosocialoauth.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener,
		LocationListener {

	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;
	private long UPDATE_INTERVAL = 60000;  /* 60 secs */
	private long FASTEST_INTERVAL = 5000; /* 5 secs */
	 
	  static final LatLng HAMBURG = new LatLng(16.0231249,-23.9886474);
	  static final LatLng KIEL = new LatLng(16.0231249,-23.9886474);
	  GoogleMap mGoogleMap;
	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);

		mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		
		if (mapFragment != null) {
			
			mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
			
			// Creating GoogleMap from SupportMapFragment
			mGoogleMap = mapFragment.getMap();
			
			// Enabling MyLocation button for the Google Map
			mGoogleMap.setMyLocationEnabled(true);
			
			// Setting OnClickEvent listener for the GoogleMap
			mGoogleMap.setOnMapClickListener(new OnMapClickListener() {
				@Override
				public void onMapClick(LatLng latlng) {
					addMarker(latlng);
					sendToServer(latlng);
				}
			});
			
			// Starting locations retrieve task
		     new RetrieveTask().execute();
			
		} else {
			Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
		}

		
		
	}

	
	// Adding marker on the GoogleMaps
	private void addMarker(LatLng latlng) {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latlng);
		markerOptions.title(latlng.latitude + "," + latlng.longitude);
		mGoogleMap.addMarker(markerOptions);
	}
	
	
	// Invoking background thread to store the touched location in Remove MySQL server
		private void sendToServer(LatLng latlng) {
			new SaveTask().execute(latlng);
		}
		// Background thread to save the location in remove MySQL server
		private class SaveTask extends AsyncTask<LatLng, Void, Void> {
			@Override
			protected Void doInBackground(LatLng... params) {
				String lat = Double.toString(params[0].latitude);
				String lng = Double.toString(params[0].longitude);
				String strUrl = "http://172.16.57.2/location_marker_mysql/save.php";					
				URL url = null;
				try {
					url = new URL(strUrl);

					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
							connection.getOutputStream());

					outputStreamWriter.write("lat=" + lat + "&lng="+lng);				
					outputStreamWriter.flush();
					outputStreamWriter.close();
					
					InputStream iStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new
					InputStreamReader(iStream));
					
					StringBuffer sb = new StringBuffer();
					
					String line = "";
					
					while( (line = reader.readLine()) != null){
						sb.append(line);
					}

					reader.close();
					iStream.close();
								

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

		}
		
		
		// Background task to retrieve locations from remote mysql server
		private class RetrieveTask extends AsyncTask<Void, Void, String>{

			@Override
			protected String doInBackground(Void... params) {
				String strUrl = "http://172.16.57.2/location_marker_mysql/retrieve.php";				
				URL url = null;
				StringBuffer sb = new StringBuffer();
				try {
					url = new URL(strUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.connect();
					InputStream iStream = connection.getInputStream();				
					BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));			
					String line = "";				
					while( (line = reader.readLine()) != null){
						sb.append(line);
					}

					reader.close();
					iStream.close();							

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}		
				return sb.toString();
			}
			
			@Override
			protected void onPostExecute(String result) {			
				super.onPostExecute(result);
				//new ParserTask().execute(result);
			}
			
		}
		
		
    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            // Map is ready
            Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
            map.setMyLocationEnabled(true);

            // Now that map has loaded, let's get our location!
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();

            connectClient();
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void connectClient() {
        // Connect the client.
        if (isGooglePlayServicesAvailable() && mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    /*
     * Called when the Activity becomes visible.
    */
    @Override
    protected void onStart() {
        super.onStart();
        connectClient();
    }

    /*
	 * Called when the Activity is no longer visible.
	 */
	@Override
	protected void onStop() {
		// Disconnecting the client invalidates it.
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
		super.onStop();
	}

	/*
	 * Handle results returned to the FragmentActivity by Google Play services
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Decide what to do based on the original request code
		switch (requestCode) {

		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
			/*
			 * If the result code is Activity.RESULT_OK, try to connect again
			 */
			switch (resultCode) {
			case Activity.RESULT_OK:
				mGoogleApiClient.connect();
				break;
			}

		}
	}

	private boolean isGooglePlayServicesAvailable() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.d("Location Updates", "Google Play services is available.");
			return true;
		} else {
			// Get the error dialog from Google Play services
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this,
					CONNECTION_FAILURE_RESOLUTION_REQUEST);

			// If Google Play services can provide an error dialog
			if (errorDialog != null) {
				// Create a new DialogFragment for the error dialog
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(errorDialog);
				errorFragment.show(getSupportFragmentManager(), "Location Updates");
			}

			return false;
		}
	}

	/*
	 * Called by Location Services when the request to connect the client
	 * finishes successfully. At this point, you can request the current
	 * location or start periodic updates
	 */
	@Override
	public void onConnected(Bundle dataBundle) {
		// Display the connection status
		Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		if (location != null) {
			Toast.makeText(this, "GPS location was found!", Toast.LENGTH_SHORT).show();
			LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
			map.animateCamera(cameraUpdate);
            startLocationUpdates();
        } else {
			Toast.makeText(this, "Current location was null, enable GPS on emulator!", Toast.LENGTH_SHORT).show();
		}
	}

    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
    }

    public void onLocationChanged(Location location) {
        // Report to the UI that the location was updated
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /*
     * Called by Location Services if the connection to the location client
     * drops because of an error.
     */
    @Override
    public void onConnectionSuspended(int i) {
        if (i == CAUSE_SERVICE_DISCONNECTED) {
            Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
        } else if (i == CAUSE_NETWORK_LOST) {
            Toast.makeText(this, "Network lost. Please re-connect.", Toast.LENGTH_SHORT).show();
        }
    }

	/*
	 * Called by Location Services if the attempt to Location Services fails.
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
		 * Google Play services can resolve some errors it detects. If the error
		 * has a resolution, try sending an Intent to start a Google Play
		 * services activity that can resolve error.
		 */
		if (connectionResult.hasResolution()) {
			try {
				// Start an Activity that tries to resolve the error
				connectionResult.startResolutionForResult(this,
						CONNECTION_FAILURE_RESOLUTION_REQUEST);
				/*
				 * Thrown if Google Play services canceled the original
				 * PendingIntent
				 */
			} catch (IntentSender.SendIntentException e) {
				// Log the error
				e.printStackTrace();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Sorry. Location services not available to you", Toast.LENGTH_LONG).show();
		}
	}

	// Define a DialogFragment that displays the error dialog
	public static class ErrorDialogFragment extends DialogFragment {

		// Global field to contain the error dialog
		private Dialog mDialog;

		// Default constructor. Sets the dialog field to null
		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		// Set the dialog to display
		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		// Return a Dialog to the DialogFragment.
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

}
