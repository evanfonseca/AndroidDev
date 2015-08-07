package cvturismo.cidade.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.exemplosocialoauth.R;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class InfoActivityCidade extends Activity {
	// DB Class to perform DB related operations
	DBController controller = new DBController(this);
	// Progress Dialog Object
	ProgressDialog prgDialog;
	HashMap<String, String> queryValues;
	// Connection detector class

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		
		// Get User records from SQLite DB
		ArrayList<HashMap<String, String>> cidadeList = controller.getAllCidade();
		// If users exists in SQLite DB
		
		if (cidadeList.size() != 0) {
			// Set the User Array list in ListView
			ListAdapter adapter = new SimpleAdapter(InfoActivityCidade.this, cidadeList, R.layout.info_screen, new String[] {
			"Id","Nome","Descricao","Ilha","Latitude_centro","Longitude_centro",
			"Historia","Clima","Geografia","Criado_em","Actualisado_em","GestorId",
			"Gestor","LugarQuantidade","FotoCapa"
			}, new int[] { 
			R.id.Id,R.id.Name,R.id.Descricao,R.id.Ilha,R.id.Latitude_centro,R.id.Longitude_centro,R.id.Historia 
			,R.id.Clima,R.id.Geografia,R.id.Criado_em,R.id.Actualisado_em,R.id.GestorId,R.id.Gestor
			,R.id.LugarQuantidade,R.id.FotoCapa		
			});
			ListView myList = (ListView) findViewById(android.R.id.list);
			myList.setAdapter(adapter);
		}
		
		
		// Initialize Progress Dialog properties
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Transferring Data from Remote MySQL DB and Syncing SQLite. Please wait...");
		prgDialog.setCancelable(false);
		
	}
	
	// Options Menu (ActionBar Menu)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// When Options Menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. 
		int id = item.getItemId();
		// When Sync action button is clicked
		if (id == R.id.refresh) {
			// Transfer data from remote MySQL DB to SQLite on Android and perform Sync
		
			controller.deleteAllCidade();
			syncSQLiteMySQLDB();
			
			return true;
		}
		
	
		return super.onOptionsItemSelected(item);
	}
	
	// Method to Sync MySQL to SQLite DB
	public void syncSQLiteMySQLDB() {
		
		// Create AsycHttpClient object
		AsyncHttpClient asyncHttpClientCidade = new AsyncHttpClient();
		
		// Http Request Params Object
		//RequestParams params = new RequestParams();
	   asyncHttpClientCidade.addHeader("Accept", "application/json");
	   asyncHttpClientCidade.addHeader("content-type", "application/json");
		
	
		// Show ProgressBar
		prgDialog.show();
		// Make Http call to getusers.php
		
		
			
	    asyncHttpClientCidade.get("http://172.16.97.145/proj/cvturismo/frontend/web/v1/cidade", new AsyncHttpResponseHandler() {
		
			@Override
				public void onSuccess(String response) {
					// Hide ProgressBar
					prgDialog.hide();
					// Update SQLite DB with response sent by getusers.php
					
					updateSQLiteCidade(response);
				}
				// When error occured
				@Override
				public void onFailure(int statusCode, Throwable error, String content) {
					// TODO Auto-generated method stub
					// Hide ProgressBar
					
					
					
					prgDialog.hide();
					if (statusCode == 404) {
						Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
					} else if (statusCode == 500) {
						Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
								Toast.LENGTH_LONG).show();
					}
				}
		});
	
		
	}
	
	public void updateSQLiteCidade(String response){
		
		ArrayList<HashMap<String, String>> usersynclist;
		usersynclist = new ArrayList<HashMap<String, String>>();
		
		try {
			// Extract JSON array from the response
			JSONArray arr = new JSONArray(response);
			System.out.println(arr.length());
			// If no of array elements is not zero
			if(arr.length() != 0){
				// Loop through each array element, get JSON object which has userid and username
				for (int i = 0; i < arr.length(); i++) {
			
					// Get JSON object
					JSONObject obj = (JSONObject) arr.get(i);
					
					System.out.println(obj.get("id"));
					System.out.println(obj.get("nome"));
					System.out.println(obj.get("descricao"));
					System.out.println(obj.get("ilha"));
					System.out.println(obj.get("latitude_centro"));
					System.out.println(obj.get("longitude_centro"));
					System.out.println(obj.get("historia"));
					System.out.println(obj.get("clima"));
					System.out.println(obj.get("geografia"));
					System.out.println(obj.get("criado_em"));
					System.out.println(obj.get("actualisado_em"));
					System.out.println(obj.get("gestorId"));
					System.out.println(obj.get("gestor"));
					System.out.println(obj.get("lugarQuantidade"));
					System.out.println(obj.get("fotoCapa"));
					
					
					// DB QueryValues Object to insert into SQLite
					queryValues = new HashMap<String, String>();
					// Add ID extracted from Object
					queryValues.put("Id", obj.get("id").toString());
					// Add Name extracted from Object
					queryValues.put("Nome", obj.get("nome").toString());
					// Add Name extracted from Object
			     	queryValues.put("Descricao", obj.get("descricao").toString());		     	
		 			// Add Name extracted from Object
					queryValues.put("Ilha", obj.get("ilha").toString());
					
					// Add Name extracted from Object
					queryValues.put("Latitude_centro", obj.get("latitude_centro").toString());
					// Add Name extracted from Object
					queryValues.put("Longitude_centro", obj.get("longitude_centro").toString());
					// Add Name extracted from Object
					queryValues.put("Historia", obj.get("historia").toString());
					// Add Name extracted from Object
					queryValues.put("Clima", obj.get("clima").toString());
					// Add Name extracted from Object
					queryValues.put("Geografia", obj.get("geografia").toString());
					// Add Name extracted from Object
					queryValues.put("Criado_em", obj.get("criado_em").toString());
					// Add Name extracted from Object
					queryValues.put("Actualisado_em", obj.get("actualisado_em").toString());
					// Add Name extracted from Object
					queryValues.put("Geografia", obj.get("geografia").toString());
					// Add Name extracted from Object
					queryValues.put("GestorId", obj.get("gestorId").toString());
					// Add Name extracted from Object
					queryValues.put("Gestor", obj.get("gestor").toString());	
					// Add Name extracted from Object
					queryValues.put("LugarQuantidade", obj.get("lugarQuantidade").toString());
					
					// Add Name extracted from Object
					queryValues.put("FotoCap", obj.get("fotoCapa").toString());
					
					// Insert Cidade into SQLite DB
					
					//controller.deleteAll();
					controller.insertCidade(queryValues);
					
				
					HashMap<String, String> map = new HashMap<String, String>();
					// Add status for each User in Hashmap
					map.put("Id", obj.get("id").toString());
					map.put("status", "1");
					usersynclist.add(map);
				
			
				}
				// Inform Remote MySQL DB about the completion of Sync activity by passing Sync status of Users
				// Reload the Main Activity
				reloadActivity();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// Reload MainActivity
	public void reloadActivity() {
		Intent objIntent = new Intent(getApplicationContext(), InfoActivityCidade.class);
		startActivity(objIntent);
	}
	
	
	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}

	
}
