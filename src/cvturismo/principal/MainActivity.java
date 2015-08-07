package cvturismo.principal;


import br.exemplosocialoauth.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import cvturismo.cidade.parsers.InfoActivityCidade;
import cvturismo.definicoes.*;
import cvturismo.gallery.GalleryMainActivity;





 
public class MainActivity extends Definition implements OnClickListener{ 
	
	int idcidade;
	String nomecidade="Benvindo à Cabo Verde!";
	TextView boasvindas;
	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;
	
    
	 @Override 
    protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);  
		
		///////Obter dados com sharedpreferences
	    app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
	    String city = app_preferences.getString("shCidade", null);
	      
	    if (city != null) 
		{ 
	    	//Toast.makeText(MainActivity.this,"Cidade Guardada "+city, Toast.LENGTH_LONG).show();
	    	nomecidade=city;	
		} 	
	    
	    
	    
		////////Recuperar dados guardados com savedInstanceState
	    
	    
		if (savedInstanceState != null) 
		{ 	
			if (savedInstanceState.getSerializable("mNomeCidade") != null) 
	 			{ 
					nomecidade=(String) savedInstanceState.getSerializable("mNomeCidade");
					//Toast.makeText(MainActivity.this,"Guardou "+(String) savedInstanceState.getSerializable("mNomeCidade"), Toast.LENGTH_LONG).show();
		        }      
		}
		
		
		final LinearLayout cidade = (LinearLayout) this.findViewById(R.id.cidade_button);
		final LinearLayout lugar = (LinearLayout) this.findViewById(R.id.place_button);
		final LinearLayout galeria = (LinearLayout) this.findViewById(R.id.gallery_button);
		final LinearLayout mapa = (LinearLayout) this.findViewById(R.id.map_button);
		final LinearLayout info = (LinearLayout) this.findViewById(R.id.info_button);
		final LinearLayout favorito = (LinearLayout) this.findViewById(R.id.favorites_button);
		final LinearLayout evento = (LinearLayout) this.findViewById(R.id.event_button);
		final LinearLayout definicao = (LinearLayout) this.findViewById(R.id.definition_button);
		boasvindas = (TextView) this.findViewById(R.id.app_name);
		
	
		boasvindas.setText(nomecidade);
		
		cidade.setOnClickListener(this);
		lugar.setOnClickListener(this);
		galeria.setOnClickListener(this);
		mapa.setOnClickListener(this);
		info.setOnClickListener(this);
		favorito.setOnClickListener(this);
		evento.setOnClickListener(this);
		definicao.setOnClickListener(this);
		
		
		if (savedInstanceState == null) 
		{ 
		/*chamada do método para escolher modo de localização*/
			escolherModo();
		}
		
		
		

		
	 }
  
	 /*Método para escolher modo de localização*/
	 public void escolherModo(){
		final CharSequence[] items = {"Modo GPS", "Escolher Cidade"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Escolha um modo de localização");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	if(item==0)   
		    	{
		    		Toast.makeText(MainActivity.this, 
		    			    "Escolheste Modo GPS", Toast.LENGTH_LONG).show();	
		    	}
		    	
		    	if(item==1)   
		    	{
		    		escolherCidade();
		    	}
		    	dialog.dismiss();
		    }
		});
		AlertDialog alert = builder.create();
		
		alert.show();
		 
		 
	 }
	 
	 
	 
	 public void escolherCidade(){
			final CharSequence[] items = {"Praia", "Mindelo","Santa Maria", "Paúl","Nova Sintra"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Escolha uma cidade!");
			builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int itemc) {
			    	nomecidade = items[itemc].toString();
			    	/////////Guardar dados com SharedPrefences
					editor = app_preferences.edit();
				    editor.putString("shCidade", nomecidade);
				    editor.commit(); // Very important
			    	//Toast.makeText(MainActivity.this,"Cidade Guardadaaaaaaaa1 "+nomecidade, Toast.LENGTH_LONG).show();
			    	if(itemc < 5) {
			    					//Toast.makeText(MainActivity.this,nomecidade, Toast.LENGTH_LONG).show(); 
			    					boasvindas.setText(nomecidade); 
			    					dialog.dismiss();
			    	}
			    	
			    	
			    }
			});
			AlertDialog alertc = builder.create();
			alertc.show();			 
		 }
	 
	 
	 
	 
	 
	 
	 
	 
       
       @Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.cidade_button:
					Intent i = new Intent(getApplicationContext(),InfoActivityCidade.class);
					startActivity(i);
					break;
				case R.id.place_button:
					Intent i1 = new Intent(getApplicationContext(),Category_selector.class);
					startActivity(i1);
					break;
				case R.id.gallery_button:
					Intent i2 = new Intent(getApplicationContext(),GalleryMainActivity.class);
					startActivity(i2);
					break;
				case R.id.map_button:
					Intent i3= new Intent(getApplicationContext(),cvturismo.maps.MapActivity.class);
					startActivity(i3);
					break;
				case R.id.info_button:
					Intent i4= new Intent(getApplicationContext(),InfLocation.class);
		    		startActivity(i4);
		    		break;
				case R.id.favorites_button:
					Intent i5= new Intent(getApplicationContext(),ListViewActivity.class);
		    		startActivity(i5);
		    		break;
				case R.id.event_button:
					Intent i6= new Intent(getApplicationContext(),ListViewActivity.class);
			   		startActivity(i6);
			   		break;
				case R.id.definition_button:
					Intent i7= new Intent(getApplicationContext(),DefinitionActivity.class);
			 		startActivity(i7);
			 		break;
			 	default:
			 		break;
			}
       }
       
       
       //////////Ao terminar activity Guarda dados para próxima CHAMADA
       @Override
       protected void onSaveInstanceState(Bundle state) {
           
           state.putSerializable("mNomeCidade", nomecidade);
           super.onSaveInstanceState(state);
       }


       
       
}
       