package cvturismo.principal;

import br.exemplosocialoauth.R;
import android.app.Activity;
import android.os.Bundle;

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


public class CidadeActivity extends Activity implements OnClickListener {
	// DB Class to perform DB related operations
	DBController controller;
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cidade_activity);
		
		
		
		tvCarregaTexto=(TextView) findViewById(R.id.T_V_CarregaTexto);
		
		tvCarregaTexto.setText("EM TESTE");
		
		Intent i1 = getIntent();  
		idcidade = i1.getIntExtra("idcidade",0); 
		
		
		
		controller=new DBController(this);
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
 
     
        
        
		/*
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
