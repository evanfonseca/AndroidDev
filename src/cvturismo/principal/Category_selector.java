package cvturismo.principal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import br.exemplosocialoauth.R;
import cvturismo.cidade.parsers.InfoActivityLugar;
import cvturismo.definicoes.Definition;



public class Category_selector extends Definition implements OnClickListener{ 

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.category_selector);
		
		
		
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
  	 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btn_ver_monomento_sitio_btn:
			Intent i4= new Intent(getApplicationContext(),InfoActivityLugar.class);
    		startActivity(i4);
    		break;
		case R.id.btn_hoteis_pensoes_btn:
			Intent i5= new Intent(getApplicationContext(),InfoActivityLugar.class);
    		startActivity(i5);
    		break;
		case R.id.btn_restaurante_bar_btn:
			Intent i6= new Intent(getApplicationContext(),SubCategory_selector.class);
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
	}
   
    

  
	

}
