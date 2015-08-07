package cvturismo.definicoes;


import cvturismo.cidade.parsers.DBController;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.exemplosocialoauth.R;
import cvturismo.cidade.parsers.*;

public class Definition extends Activity{

	// DB Class to perform DB related operations
		DBController controller = new DBController(this);
		InfoActivityCidade cntr= new InfoActivityCidade();
    
	public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);//Menu Resource, Menu  
        
        menu.findItem(R.id.logout).setVisible(true);
        return true;  
    }
	
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
 
        case R.id.location:
        	Intent ini = new Intent(this, DefinitionLocation.class);
            startActivity(ini);
            return true;
 
       /* case R.id.refresh:
        	controller.deleteAllCidade();
        	 cntr.syncSQLiteMySQLDB();
            return true;*/
 
        case R.id.sair:
            finish();
            System.exit(0);
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }   
   
    }
  
	


