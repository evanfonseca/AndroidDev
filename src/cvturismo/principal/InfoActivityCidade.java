package cvturismo.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import br.exemplosocialoauth.R;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cvturismo.cidade.parsers.DBController;
import cvturismo.gallery.GalleryAdapter;
import cvturismo.gallery.GalleryImageAdapter;

public class InfoActivityCidade extends Activity implements OnClickListener {
	// DB Class to perform DB related operations
	DBController controller = new DBController(this);
	// Progress Dialog Object
	ProgressDialog prgDialog;
	HashMap<String, String> queryValues;
	
	HashMap<String, String> cidadeEscolhida;
	
	int idcidade;
	// Connection detector class
	
	TextView tvCarregaTexto;
	ImageView desc_cidade, hist_cidade, geo_cidade, clima_cidade, eventos_cidade, contactosUteis_cidade;
	GridView gridview ;

	private ImageView leftArrowImageView;

	private ImageView rightArrowImageView;

	private Gallery gallery;

	private int selectedImagePosition = 0;
	
	private List<Drawable> drawables;

	private GalleryAdapter galImageAdapter;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cidade);
		
		tvCarregaTexto=(TextView) findViewById(R.id.T_V_CarregaTexto);
		
		tvCarregaTexto.setText("EM TESTE");
		/*
		Intent i1 = getIntent();  
		idcidade = i1.getIntExtra("idcidade",0); 
		
		Toast.makeText(InfoActivityCidade.this,"Id escolhido é "+idcidade, Toast.LENGTH_LONG).show();
		
		
		cidadeEscolhida=controller.getCidadeById(idcidade);
		
		desc_cidade=(ImageView) findViewById(R.id.btn_desc_cidade);
		desc_cidade.setOnClickListener(this);
		
		hist_cidade=(ImageView) findViewById(R.id.btn_hist_cidade);
		hist_cidade.setOnClickListener(this);
		
		geo_cidade=(ImageView) findViewById(R.id.btn_geo_cidade);
		geo_cidade.setOnClickListener(this);
		
		clima_cidade=(ImageView) findViewById(R.id.btn_clima_cidade);
		clima_cidade.setOnClickListener(this);
		
		eventos_cidade=(ImageView) findViewById(R.id.bnt_eventos_cidade);
		eventos_cidade.setOnClickListener(this);
		
		contactosUteis_cidade=(ImageView) findViewById(R.id.bnt_contactos_uteis_cidade);
		contactosUteis_cidade.setOnClickListener(this);
		
		
		
		
		tvCarregaTexto.setText(cidadeEscolhida.get("Descricao"));
		desc_cidade.setBackgroundResource(R.drawable.list_bg_focus);
		
		getDrawablesList();
		setupUI();
		
		
		 // Get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
        GalleryImageAdapter imageAdapter = new GalleryImageAdapter(this);
 
     
        
        
		
		// Initialize Progress Dialog properties
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Transferring Data from Remote MySQL DB and Syncing SQLite. Please wait...");
		prgDialog.setCancelable(false);
		*/
	}
	
	
        private void setupUI() {
		

        leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
    	rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
    	rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		gallery = (Gallery) findViewById(R.id.gallery);

		
		
		
		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition > 0) {
					--selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, true);
			}
		});
		
		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition < drawables.size() - 1) {
					++selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);

			}
		});
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				selectedImagePosition = pos;

				if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

				} else if (selectedImagePosition == 0) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

				} else if (selectedImagePosition == drawables.size() - 1) {

					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
				}
				

				//changeBorderForSelectedImage(selectedImagePosition);
			//	setSelectedImage(selectedImagePosition);
				
			}
			

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		galImageAdapter = new GalleryAdapter(this, drawables);
		gallery.setAdapter(galImageAdapter);
		
		

		if (drawables.size() > 0) {

			gallery.setSelection(selectedImagePosition, false);

		}

		if (drawables.size() == 1) {

			rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
		}
	

	}
	private void getDrawablesList() {
		
		
		drawables = new ArrayList<Drawable>();
		
		drawables.add(getResources().getDrawable(R.drawable.natureimage1));
		drawables.add(getResources().getDrawable(R.drawable.natureimage2));
		drawables.add(getResources().getDrawable(R.drawable.natureimage3));
		drawables.add(getResources().getDrawable(R.drawable.natureimage4));
		drawables.add(getResources().getDrawable(R.drawable.natureimage5));
		drawables.add(getResources().getDrawable(R.drawable.natureimage6));
		drawables.add(getResources().getDrawable(R.drawable.natureimage7));
		drawables.add(getResources().getDrawable(R.drawable.natureimage8));
		drawables.add(getResources().getDrawable(R.drawable.natureimage9));
		drawables.add(getResources().getDrawable(R.drawable.natureimage10));
		drawables.add(getResources().getDrawable(R.drawable.natureimage11));
		drawables.add(getResources().getDrawable(R.drawable.natureimage12));
		drawables.add(getResources().getDrawable(R.drawable.natureimage13));
		drawables.add(getResources().getDrawable(R.drawable.natureimage14));
		drawables.add(getResources().getDrawable(R.drawable.natureimage15));

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

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_desc_cidade:
							{
								tvCarregaTexto.setText(cidadeEscolhida.get("Descricao"));
								desc_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								hist_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								geo_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								clima_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								eventos_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								contactosUteis_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
							}
							break;
		case R.id.btn_hist_cidade:
							{
								tvCarregaTexto.setText(cidadeEscolhida.get("Historia"));
								hist_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								desc_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								geo_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								clima_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								eventos_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								contactosUteis_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								
							}
							break;
		case R.id.btn_geo_cidade:
							{
								tvCarregaTexto.setText(cidadeEscolhida.get("Geografia"));
								geo_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								hist_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								desc_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								clima_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								eventos_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								contactosUteis_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
							}
							break;
		case R.id.btn_clima_cidade:
							{
								tvCarregaTexto.setText(cidadeEscolhida.get("Clima"));
								clima_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								geo_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								hist_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								desc_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								eventos_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								contactosUteis_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								
							}
							break;
		case R.id.bnt_eventos_cidade:
							{
								///////CODIGO PARA EVENTOS AQUIIIIII
								tvCarregaTexto.setText("PRÓXIMOS EVENTOS!!!");
								eventos_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								clima_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								geo_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								hist_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								desc_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								contactosUteis_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								
							}
							break;
		case R.id.bnt_contactos_uteis_cidade:
							{
								///////CODIGO PARA CONTACTOS AQUIIIIII
								tvCarregaTexto.setText("CONTACTOS UTEIS!!!");
								contactosUteis_cidade.setBackgroundResource(R.drawable.list_bg_focus);
								eventos_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								clima_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								geo_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								hist_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								desc_cidade.setBackgroundResource(R.drawable.btn_ver_btn);
								
							}
							break;					
												
								
		
	 	default:
	 		break;
	}
	}

	



	
}
