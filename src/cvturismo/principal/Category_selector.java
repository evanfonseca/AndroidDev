package cvturismo.principal;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import br.exemplosocialoauth.R;
import cvturismo.cidade.parsers.DBController;
import cvturismo.cidade.parsers.ActivityListViewLugar;
import cvturismo.definicoes.Definition;



public class Category_selector extends Definition implements OnClickListener{ 
	
	//DBController controller = new DBController(this);
	int idcidade;
	ArrayList<HashMap<String, String>> LugaresCidade;
	
	String[] itemname ={
			"Safari",
			"Camera",
			"Global",
			"FireFox",
			"UC Browser",
			"Android Folder",
			"VLC Player",
			"Cold War"
		};
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.categorias);
		
		/*setContentView(R.layout.category_selector);*/
		
		Intent i1 = getIntent();  
		idcidade = i1.getIntExtra("idcidade",0);
		String nomeLugares="";
		
		//this.setListAdapter(new ArrayAdapter<String>(this, R.layout.lista_item_categoria,R.id.Itemname,itemname));
		
		
		/*
		final LinearLayout btn_ver_monomento_sitio_btn = (LinearLayout) this.findViewById(R.id.btn_ver_monomento_sitio_btn );
		final LinearLayout btn_hoteis_pensoes_btn= (LinearLayout) this.findViewById(R.id. btn_hoteis_pensoes_btn);
		final LinearLayout btn_restaurante_bar_btn = (LinearLayout) this.findViewById(R.id.btn_restaurante_bar_btn);
		final LinearLayout btn_centro_comercial_btn = (LinearLayout) this.findViewById(R.id.btn_centro_comercial_btn);
		final LinearLayout btn_ponto_interece_btn = (LinearLayout) this.findViewById(R.id.btn_ponto_interece_btn);
		final LinearLayout btn_servico_btn = (LinearLayout) this.findViewById(R.id.btn_servico_btn);

		btn_ver_monomento_sitio_btn.setOnClickListener(this);
		btn_hoteis_pensoes_btn.setOnClickListener(this);
		btn_restaurante_bar_btn.setOnClickListener(this);
		btn_centro_comercial_btn.setOnClickListener(this);
		btn_ponto_interece_btn.setOnClickListener(this);
		btn_servico_btn.setOnClickListener(this);
		*/
  	 
	}

	
	
	
	
	private void setListAdapter(ArrayAdapter<String> arrayAdapter) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onClick(View v) {
		
		/*
		Toast.makeText(Category_selector.this,"Partiu: ", Toast.LENGTH_LONG).show();
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btn_ver_monomento_sitio_btn:
			
			Intent i4= new Intent(getApplicationContext(),InfoActivityLugar.class);
			i4.putExtra("idcidade",idcidade);
			//////////////SO PARA TESTE////////////
			i4.putExtra("idcategoria",1);
    		startActivity(i4);
    		break;
		case R.id.btn_hoteis_pensoes_btn:
			Intent i5= new Intent(getApplicationContext(),InfoActivityLugar.class);
			i5.putExtra("idcidade",idcidade);
			i5.putExtra("idcategoria",2);
    		startActivity(i5);
    		break;
		case R.id.btn_restaurante_bar_btn:
			Intent i6= new Intent(getApplicationContext(),SubCategory_selector.class);
			i6.putExtra("idcidade",idcidade);
			i6.putExtra("idcategoria",3);
	   		startActivity(i6);
	   		break;
		case R.id.btn_centro_comercial_btn:
			Intent i7= new Intent(getApplicationContext(),InfoActivityLugar.class);
	 		startActivity(i7);
	 		break;
	 		
		case R.id.btn_ponto_interece_btn:
			Intent i8= new Intent(getApplicationContext(),InfoActivityLugar.class);
	 		startActivity(i8);
	 		break;
	 		
		case R.id.btn_servico_btn:
			Intent i9= new Intent(getApplicationContext(),InfoActivityLugar.class);
	 		startActivity(i9);
	 		break;
	 		
	 	default:
	 		break;
		}
	*/
	}
   
    

  
	

}
