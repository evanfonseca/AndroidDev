package cvturismo.principal;






import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import br.exemplosocialoauth.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import cvturismo.cidade.parsers.DBController;
import cvturismo.cidade.parsers.InfoActivityCidade;
import cvturismo.definicoes.*;
import cvturismo.gallery.GalleryMainActivity;
import android.app.Activity;


public class MMainActivity extends Activity implements OnClickListener{
	
	LinearLayout cidade;
	LinearLayout lugar;
	LinearLayout galeria;
	LinearLayout mapa;
	LinearLayout info;
	LinearLayout favorito;
	LinearLayout evento;
	LinearLayout definicao;
	
	TextView boasvindas;
	RelativeLayout relativeLayout;
	ImageView background_image;
	
	Context mContext;
	
	String benvindo="Benvindo à Cabo Verde!";
	String nomecidade;
	
	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;
	// DB Class to perform DB related operations
	DBController controller;
	ArrayList<HashMap<String, String>> cidadeList;
	String[] arrayNomes;
	Integer[] arrayIds;
	ArrayList<String> listString;
	List<Object> listInt;
	
	int idCity;
	int idcidade=0;
	boolean chama_escolha=false, carregou=false;
	Button teste, carregarBD;
	
	// Keep all Images in array
    public Integer[] mThumbIds = {	R.drawable.images0,
            						R.drawable.images1, 
            						R.drawable.images2,
            						R.drawable.images3, 
            						R.drawable.images4
            					 };
    
    // Selected image id
    int position =0;
    
    ArrayList<String> nomes;
    String[] nomefotos;
    int [] setImg = {R.drawable.images1,R.drawable.images2,R.drawable.images3};
    Timer _t;
    int i=0;
    int count=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
			
		   controller = new DBController(MMainActivity.this);
		   
		   ///////Obter dados com sharedpreferences
		    app_preferences = PreferenceManager.getDefaultSharedPreferences(MMainActivity.this);
			    
		    idcidade =  app_preferences.getInt("shIdCidade", 0);  
		    //nomecidade = app_preferences.getString("shCidade", null);
		    
		    chama_escolha=app_preferences.getBoolean("shEscolha",false);
			
		    count=app_preferences.getInt("shCount", 0);
		    			
				cidade = (LinearLayout) MMainActivity.this.findViewById(R.id.cidade_button);
				lugar = (LinearLayout) MMainActivity.this.findViewById(R.id.place_button);
				galeria = (LinearLayout)  MMainActivity.this.findViewById(R.id.gallery_button);
				mapa = (LinearLayout)  MMainActivity.this.findViewById(R.id.map_button);
				info = (LinearLayout)  MMainActivity.this.findViewById(R.id.info_button);
				favorito = (LinearLayout)  MMainActivity.this.findViewById(R.id.favorites_button);
				evento = (LinearLayout)  MMainActivity.this.findViewById(R.id.event_button);
				definicao = (LinearLayout)  MMainActivity.this.findViewById(R.id.definition_button);
				teste=(Button)  MMainActivity.this.findViewById(R.id.teste);
				
				teste.setOnClickListener( MMainActivity.this);
				cidade.setOnClickListener( MMainActivity.this);
				lugar.setOnClickListener( MMainActivity.this);
				galeria.setOnClickListener( MMainActivity.this);
				mapa.setOnClickListener( MMainActivity.this);
				info.setOnClickListener( MMainActivity.this);
				favorito.setOnClickListener( MMainActivity.this);
				evento.setOnClickListener( MMainActivity.this);
				definicao.setOnClickListener( MMainActivity.this);
				
				boasvindas = (TextView) MMainActivity.this.findViewById(R.id.app_name);
				
				relativeLayout = (RelativeLayout) MMainActivity.this.findViewById(R.id.RootView);
				
				background_image =  (ImageView) MMainActivity.this.findViewById(R.id.background_image);
 
			    position = idcidade;
			    
			    if (idcidade == 0) 
					{ 	
						
			    		boasvindas.setText(benvindo);
			    		background_image.setImageBitmap(MMainActivity.this.CarregaBitmap(400,400,mThumbIds[0]));
						
			    		MMainActivity.this.escolherModo();
			    		
					}
			    else{
			    		//nomecidade=
			    		HashMap<String, String> map = controller.getCidadeById(idcidade);
			    		nomecidade=map.get("Nome");
			    		boasvindas.setText(nomecidade);
	
			    		MMainActivity.this.loadDrawbleName();
			            background_image.setImageBitmap(MMainActivity.this.CarregaBitmap(400,400,setImg[count]));
						count=count+1;
						MMainActivity.this.switchBackground();
					}
			
	}
	
	public void loadDrawbleName(){
		
		nomes = controller.getAllFotoCapaCidade(idcidade);
		nomefotos = nomes.toArray(new String[nomes.size()]);
		
		int tamanhoListaFotos =nomefotos.length;
		int i=0, drawableId = 0;
		
		String drawableName="";
		
		
		int[] lint = new int[tamanhoListaFotos];
		for(i=0;i<tamanhoListaFotos;i++)
		{	
			drawableName=nomefotos[i];
	        try {
			    Class res = R.drawable.class;
			    java.lang.reflect.Field field = res.getField(drawableName);
			    
			    
			    drawableId = field.getInt(null);
			   
			    lint[i]=drawableId;
			    
			    
			}
			catch (Exception e) {
			    Log.e("MyTag", "Failure to get drawable id.", e);
			}
	        
		}
        
        

        setImg=lint;
	}
	
	
	
	
	public void switchBackground(){
		
		
		
		 _t = new Timer();
		  _t.scheduleAtFixedRate(new TimerTask() {
	           @Override
	           public void run() {
	
	               runOnUiThread(new Runnable() // run on ui thread
	               {
	                   public void run() {
	                       if (count < setImg.length) {
	
	                    	   background_image.setImageBitmap(CarregaBitmap(400,400,setImg[count]));
	                    	   //background_image.setImageBitmap(BitmapFactory.decodeResource(getResources(), setImg[count]));
	                           count = (count + 1) % setImg.length;
	                           
	                           editor = app_preferences.edit();
	           				   editor.putInt("shCount", count);
	           				   editor.commit(); // Very important
	                       }
	                   }
	               });
	           }
	       }, 5000, 5000);
		
		
	}
	
	private Bitmap CarregaBitmap(int alturaDesejada, int larguraDesejada, int imageID) {
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		
		BitmapFactory.decodeResource(getResources(), imageID, options);
		final int altura = options.outHeight;
		final int largura = options.outWidth;
		
		
		if(altura >alturaDesejada || largura > larguraDesejada){
			final int taxaAltura = Math.round((float) altura/ (float) alturaDesejada);
			final int taxaLargura = Math.round((float) largura/ (float) larguraDesejada);
			
			options.inSampleSize = taxaAltura < taxaLargura ? taxaAltura : taxaLargura;
			
		}
		
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(getResources(), imageID, options);
	} 
	
	
	
	
	public void escolherModo(){
		chama_escolha= true;
		editor = app_preferences.edit();
		editor.putBoolean("shEscolha", chama_escolha);
		editor.commit(); // Very important
		final CharSequence[] items = {"Modo GPS", "Escolher Cidade"};
		
		 ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_IAPTheme);
		 final AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		
		
		builder.setTitle("Escolha um modo de localização");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	
		    	if(item==0)   
		    	{
		    		
		    		Toast.makeText(MMainActivity.this, 
		    			    "Escolheste Modo GPS", Toast.LENGTH_LONG).show();
		    					/////////Guardar dados com SharedPrefences
		    	}
		    	
		    	if(item==1)   
		    	{
		    		escolherCidade();
		    	}
		    	dialog.dismiss();
		    	
		    	//editor.putBoolean("mEscolheu", escolheu);
		    }
		});
		AlertDialog alert = builder.create();
		
		alert.show();
		 
		 
	 }
	
	
	
	
	
	public void escolherCidade(){
		 
	 	// Lista de cidades do SQLiteDB
	 	controller = new DBController(this);
		cidadeList = controller.getAllCidade();
		
		listString = new ArrayList<String>();
        
        listInt = new ArrayList<Object>();
	 
	 ///Carregar Arrays Lista de Cidades e Lista de IDs
        int x;
        for(HashMap<String,String> row : cidadeList){
            listString.add(row.get("Nome"));
            
            x= Integer.parseInt(row.get("Id"));
            listInt.add(x);
        }
        
        ///Preparar Arrays[] para ALERTDIALOG
        arrayNomes = listString.toArray(new String[listString.size()]);
        arrayIds = (Integer[]) listInt.toArray(new Integer[listInt.size()]);
	 	final int tamanho = listString.size();
	 	
		final CharSequence[] items = arrayNomes;
		
		 ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_IAPTheme);
		 
		 final AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		
		builder.setTitle("Escolha uma cidade!");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		    
			
			public void onClick(DialogInterface dialog, int itemc) {
		    	nomecidade = items[itemc].toString();
		    	idcidade =  arrayIds[itemc];
		    	position = idcidade;
		    	
		    	/////////Guardar dados com SharedPrefences
				editor = app_preferences.edit();
				editor.putString("shCidade", nomecidade);
				editor.putInt("shIdCidade", idcidade);
				editor.commit(); // Very important
		    	
		    	//Toast.makeText(MainActivity.this,"Cidade Guardadaaaaaaaa1 "+nomecidade, Toast.LENGTH_LONG).show();
		    	if(itemc < tamanho) {
		    		
		    					boasvindas.setText(nomecidade);
		    					
		    					
		    					loadDrawbleName();
		    		            background_image.setImageBitmap(CarregaBitmap(200,200,setImg[0]));
		    					count=1;
		    					switchBackground();
		    					
		    					
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
					Intent i = new Intent(getApplicationContext(),CidadeActivity.class);
					i.putExtra("idcidade",idcidade);
					startActivity(i);
					break;
				case R.id.place_button:
					Intent i1 = new Intent(getApplicationContext(),Select_Category.class);
					i1.putExtra("idcidade",idcidade);
					startActivity(i1);
					break;
				case R.id.gallery_button:
					Intent i2 = new Intent(getApplicationContext(),cvturismo.gallery.Galeria.class);
					startActivity(i2);
					break;
				case R.id.map_button:
					Intent i3= new Intent(getApplicationContext(),cvturismo.maps.MapActivity.class);
					i3.putExtra("idcidade",idcidade);
					startActivity(i3);
					break;
				case R.id.info_button:
					Intent i4= new Intent(getApplicationContext(),InfLocation.class);
		    		startActivity(i4);
		    		break;
				case R.id.favorites_button:
					Intent i5= new Intent(getApplicationContext(),InfLocation.class);
		    		startActivity(i5);
		    		break;
				case R.id.event_button:
					Intent i6= new Intent(getApplicationContext(),InfLocation.class);
			   		startActivity(i6);
			   		break;
				case R.id.definition_button:
					//Intent i7= new Intent(getApplicationContext(),DefinitionActivity.class);
			 		//startActivity(i7);
					escolherModo();
					break;
			 	case R.id.teste:
					Intent i8= new Intent(getApplicationContext(),Teste.class);
			 		startActivity(i8);
					break;
			 	
			 	default:
			 		break;
			}
			
    }
	
    
	 // Options Menu (ActionBar Menu)
	 	@Override
	 	public boolean onCreateOptionsMenu(Menu menu) {
	 		// Inflate the menu; this adds items to the action bar if it is present.
	 		getMenuInflater().inflate(R.menu.main, menu);
	 		return true;
	 	}
	

}
