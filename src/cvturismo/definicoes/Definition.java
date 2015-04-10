package cvturismo.definicoes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import cvturismo.R;

public class Definition extends Activity{

	
	 
   
    
	public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu  
        return true;  
    }
	
	/**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.logout:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            
            return true;
 
        case R.id.logar:
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            return true;
 
        case R.id.rpassword:
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/site/request-password-reset"));
            startActivity(in);
            return true;
 
        case R.id.registar:
        	Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/index.php?r=site%2Fsignup")); 
        	startActivity(inte);
        	return true;
 
        case R.id.localizacao:
            
            return true;
 
        case R.id.lingua:
            
            return true;
        case R.id.sair:
            finish();
            System.exit(0);
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }
  
	

}
