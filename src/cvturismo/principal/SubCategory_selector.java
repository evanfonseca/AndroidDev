package cvturismo.principal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import br.exemplosocialoauth.R;
import cvturismo.definicoes.Definition;



public class SubCategory_selector extends Definition implements OnClickListener{ 

	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.subcategory_selector);
		
		final LinearLayout btn_cafe_btn = (LinearLayout) this.findViewById(R.id.btn_cafe_btn );
		final LinearLayout btn_lanche_btn = (LinearLayout) this.findViewById(R.id.btn_lanche_btn);
		final LinearLayout btn_pizza_btn = (LinearLayout) this.findViewById(R.id.btn_pizza_btn );
		final LinearLayout btn_rest_btn = (LinearLayout) this.findViewById(R.id.btn_rest_btn);
		final LinearLayout btn_bares_noturno_btn = (LinearLayout) this.findViewById(R.id.btn_bares_noturno_btn);
		
		btn_cafe_btn.setOnClickListener(this);
		btn_lanche_btn.setOnClickListener(this);
		btn_pizza_btn.setOnClickListener(this);
		btn_rest_btn.setOnClickListener(this);
		btn_bares_noturno_btn.setOnClickListener(this);
	 
  	 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btn_cafe_btn:
			Intent i4= new Intent(getApplicationContext(),ListViewActivity.class);
    		startActivity(i4);
    		break;
		case R.id.btn_lanche_btn:
			Intent i5= new Intent(getApplicationContext(),ListViewActivity.class);
    		startActivity(i5);
    		break;
		case R.id.btn_pizza_btn:
			Intent i6= new Intent(getApplicationContext(),ListViewActivity.class);
	   		startActivity(i6);
	   		break;
		case R.id.btn_rest_btn:
			Intent i7= new Intent(getApplicationContext(),ListViewActivity.class);
	 		startActivity(i7);
	 		break;
		case R.id.btn_bares_noturno_btn:
			Intent i8= new Intent(getApplicationContext(),ListViewActivity.class);
	 		startActivity(i8);
	 		break;
	 	default:
	 		break;
		}
	}
   
    

  
	

}
