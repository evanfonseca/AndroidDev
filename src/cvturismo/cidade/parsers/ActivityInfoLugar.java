package cvturismo.cidade.parsers;



import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.exemplosocialoauth.R;
import cvturismo.definicoes.Definition;



public class ActivityInfoLugar  extends Activity {
	
	// DB Class to perform DB related operations
		DBController controller;
		// Progress Dialog Object
		ProgressDialog prgDialog;
		HashMap<String, String> queryValues;
		ListView myList;
		int idLugar;
		int idcategoria;
		HashMap<String, String> lugar;
		HashMap<String, String> categoria;
		HashMap<String, String> contacto;
		
		String nome="";
		String discricao="";
		// Connection detector class
		
		Integer[] arrayIds;

		
		TextView Nome;
		TextView Descricao;
		TextView Categoria;
	
		
		TextView Telefone;
		TextView Email;
		TextView Linkweb;
		TextView Endereco;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lugar);
		
		
		Intent i1 = getIntent();  
		idLugar = i1.getIntExtra("idLugar",0);
		idcategoria= i1.getIntExtra("idcategoria",0);

		
		
		controller = new DBController(this);
		lugar = new HashMap<String, String>();
		lugar=controller.getLugarById(idLugar);
		
		categoria= new HashMap<String, String>();
		categoria=controller.getCategoriaById(idcategoria);
		
		contacto= new HashMap<String, String>();
		contacto=controller.getContactoLugarByIdLugar(idLugar);
		
		
		
		
		Nome=(TextView) findViewById(R.id.Nome);
		Nome.setText(lugar.get("Nome"));
		Descricao=(TextView) findViewById(R.id.Discricao);
		Descricao.setText(lugar.get("Descricao"));
		Categoria= (TextView) findViewById(R.id.category);
		Categoria.setText(categoria.get("Descricao"));
	
		
		/*.....................contacto........................*/
		
		Telefone=(TextView) findViewById(R.id.telefone);
		Email=(TextView) findViewById(R.id.email);
		Linkweb=(TextView) findViewById(R.id.linkweb);
		Endereco=(TextView) findViewById(R.id.endereco);
		
		boolean b = controller.temContacto(idLugar);
		if(b)
			{
				Telefone.setText(contacto.get("Telefone"));
				Email.setText(contacto.get("Email"));
				Linkweb.setText(contacto.get("Linkweb"));
				Endereco.setText(contacto.get("Endereco"));
			 }
		else {
				Telefone.setText("");
				Email.setText("");
				Linkweb.setText("");
				Endereco.setText("");
		  	  }
		
		/*
		nome=lugar.get("Nome");
		Toast.makeText(getApplicationContext(),nome, Toast.LENGTH_SHORT).show();
		*/

	}

	// Options Menu (ActionBar Menu)
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		// When Options Menu is selected
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. 
			int id = item.getItemId();
			// When Sync action button is clicked
			if (id == R.id.refresh) {
				// Transfer data from remote MySQL DB to SQLite on Android and perform Sync
			
				controller.deleteAllLugar();
				//syncSQLiteMySQLDB();
				
				return true;
			}
			
		
			return super.onOptionsItemSelected(item);
		}
	

    

  
	

}
