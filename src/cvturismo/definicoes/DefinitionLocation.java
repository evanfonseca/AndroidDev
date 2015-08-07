package cvturismo.definicoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import br.exemplosocialoauth.R;
import cvturismo.principal.ListViewActivity;

public class DefinitionLocation extends Definition implements OnClickListener{

	
	
	 @Override 
	    protected void onCreate(Bundle savedInstanceState) {  
			super.onCreate(savedInstanceState);
			setContentView(R.layout.definition_location); 
			
			
		    final LinearLayout  bt_ModoGPS_defLoc = (LinearLayout ) this.findViewById(R.id.bt_ModoGPS_defLoc);
		    final LinearLayout bt_ModoPerson_defLoc= (LinearLayout ) this.findViewById(R.id.bt_ModoPerson_defLoc);
			final LinearLayout sair= (LinearLayout ) this.findViewById(R.id.sair);
			
		
		bt_ModoGPS_defLoc.setOnClickListener(this);
			bt_ModoPerson_defLoc.setOnClickListener(this);
		sair.setOnClickListener(this);
	 }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()){
		 
	        case R.id.bt_ModoGPS_defLoc:
	            Intent i = new Intent(this, ListViewActivity.class);
	            startActivity(i);
	            break;
	            
	        case R.id.bt_ModoPerson_defLoc:
	        	Intent i1 = new Intent(this, ListViewActivity.class);
	            startActivity(i1);
	            
	        	break;

	        case R.id.sair:
	            finish();
	            System.exit(0);
	            break;
	 
	        default:
	        	break;
	        }
	
	  

		
			
		}
		

}
