package cvturismo.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cvturismo.R;
import cvturismo.definicoes.*;

 
public class MainActivity extends Definition implements OnClickListener{  
    
	 @Override 
    protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);  
		    
		final LinearLayout cidade = (LinearLayout) this.findViewById(R.id.cidade_button);
		final LinearLayout lugar = (LinearLayout) this.findViewById(R.id.place_button);
		final LinearLayout galeria = (LinearLayout) this.findViewById(R.id.gallery_button);
		final LinearLayout mapa = (LinearLayout) this.findViewById(R.id.map_button);
		final LinearLayout info = (LinearLayout) this.findViewById(R.id.info_button);
		final LinearLayout favorito = (LinearLayout) this.findViewById(R.id.favorites_button);
		final LinearLayout evento = (LinearLayout) this.findViewById(R.id.event_button);
		final LinearLayout definicao = (LinearLayout) this.findViewById(R.id.definition_button);
		
		cidade.setOnClickListener(this);
		lugar.setOnClickListener(this);
		galeria.setOnClickListener(this);
		mapa.setOnClickListener(this);
		info.setOnClickListener(this);
		favorito.setOnClickListener(this);
		evento.setOnClickListener(this);
		definicao.setOnClickListener(this);
	 }
       
       
       @Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.cidade_button:
					Intent i = new Intent(getApplicationContext(),AndroidListViewActivity.class);
					startActivity(i);
					break;
				case R.id.place_button:
					Intent i1 = new Intent(getApplicationContext(),AndroidListViewActivity.class);
					startActivity(i1);
					break;
				case R.id.gallery_button:
					Intent i2 = new Intent(getApplicationContext(),AndroidListViewActivity.class);
					startActivity(i2);
					break;
				case R.id.map_button:
					Intent i3= new Intent(getApplicationContext(),AndroidListViewActivity.class);
					startActivity(i3);
					break;
				case R.id.info_button:
					Intent i4= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		    		startActivity(i4);
		    		break;
				case R.id.favorites_button:
					Intent i5= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		    		startActivity(i5);
		    		break;
				case R.id.event_button:
					Intent i6= new Intent(getApplicationContext(),AndroidListViewActivity.class);
			   		startActivity(i6);
			   		break;
				case R.id.definition_button:
					Intent i7= new Intent(getApplicationContext(),cvturismo.definicoes.Definition.class);
			 		startActivity(i7);
			 		break;
			 	default:
			 		break;
			}
       }
}
       