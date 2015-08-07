package cvturismo.definicoes;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import br.exemplosocialoauth.R;

public class DefinitionActivity extends Definition implements OnClickListener{

	
	 @Override 
	    protected void onCreate(Bundle savedInstanceState) {  
			super.onCreate(savedInstanceState);
			setContentView(R.layout.definition); 
			
			
			final LinearLayout  login = (LinearLayout ) this.findViewById(R.id.bt_login_def);
			final LinearLayout logout = (LinearLayout ) this.findViewById(R.id.bt_logout_def);
			final LinearLayout location= (LinearLayout ) this.findViewById(R.id.bt_location_def);
			final LinearLayout  registar = (LinearLayout ) this.findViewById(R.id.bt_registar_def);
			final LinearLayout  rpassword= (LinearLayout ) this.findViewById(R.id.bt_rpassword_def);
			final LinearLayout  lingua = (LinearLayout ) this.findViewById(R.id.bt_lingua_def);
			final LinearLayout sair = (LinearLayout ) this.findViewById(R.id.bt_sair_def);
		
			login.setOnClickListener(this);
			logout.setOnClickListener(this);
			location.setOnClickListener(this);
			registar.setOnClickListener(this);
			rpassword.setOnClickListener(this);
		    lingua.setOnClickListener(this);
			sair.setOnClickListener(this);
	 }
	
	 @Override
    public void onClick(View v) {
			// TODO Auto-generated method stub
		 
      switch (v.getId()){
		 
        case R.id.bt_login_def:
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            break;
            
        case R.id.bt_logout_def:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            
        	break;
 
        case R.id.bt_rpassword_def:
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/site/request-password-reset"));
            startActivity(in);
            break;
 
        case R.id.bt_registar_def:
        	Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/index.php?r=site%2Fsignup")); 
        	startActivity(inte);
        	break;
 
        case R.id.bt_location_def:
            
        	Intent ini = new Intent(this, DefinitionLocation.class);
            startActivity(ini);
        	break;

       // case R.id.bt_lingua_def:
            
        //	break;
        case R.id.bt_sair_def:
            finish();
            System.exit(0);
            break;
 
        default:
        	break;
        }
	
  

	
		
	}

  
		
	}
	
	
	

