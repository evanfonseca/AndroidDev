package cvturismo.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cvturismo.cidade.model.Categoria;
import cvturismo.cidade.model.CategoriaC;
import cvturismo.cidade.parsers.DBController;
import cvturismo.cidade.parsers.ActivityListViewLugar;
import br.exemplosocialoauth.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Select_Category extends ListActivity {
	
	ListView list;
	String[] itemname, icons ;
	//Integer[] imgid;
	
	 
	
	DBController controller;
	ArrayList<HashMap<String, String>> listCat;
	ArrayList<String> listString = new ArrayList<String>();
	ArrayList<String> listStringLink_Icon = new ArrayList<String>();
	ArrayList<Object> listInt = new ArrayList<Object>();
	Integer[] drawableIcons = null;
	int[] listaRid;
	
	Integer[] arrayIds;
	
	int idcidade;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorias);
		
		
		
		Intent i1 = getIntent();  
		idcidade = i1.getIntExtra("idcidade",0);
		
		controller = new DBController(this);
		listCat=controller.getAllCategorias();
		
		listString = new ArrayList<String>();
		listStringLink_Icon=new ArrayList<String>();
		listInt = new ArrayList<Object>();
		
		int x;
        for(HashMap<String,String> row : listCat){
            listString.add(row.get("Descricao"));
            listStringLink_Icon.add(row.get("Link_icon"));
            x= Integer.parseInt(row.get("Id"));
            listInt.add(x);
        }
		
        itemname = listString.toArray(new String[listString.size()]);
        icons = listStringLink_Icon.toArray(new String[listStringLink_Icon.size()]);
        arrayIds = (Integer[]) listInt.toArray(new Integer[listInt.size()]);
        
        
        
		int tamanhoListaIcons =icons.length;
		int i=0, drawableId = 0;
		
		String drawableName="";
		
		
		int[] lint = new int[tamanhoListaIcons];
		for(i=0;i<tamanhoListaIcons;i++)
		{	
			drawableName=icons[i];
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
        
        
		Integer[] imgid = new Integer[lint.length];
        for (int z = 0; z < lint.length; z++) {
        	imgid[z] = Integer.valueOf(lint[z]);
		}

		    
		
		
		GotoAdapter adapter=new GotoAdapter(this, itemname, imgid);
		list=(ListView)findViewById(android.R.id.list);
		list.setAdapter(adapter);
		
		
		
		
		list.setOnItemClickListener(new OnItemClickListener() {
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//String Slecteditem= itemname[position];
				int idCategoria = arrayIds[position];
				
				//Toast.makeText(getApplicationContext()," idCategoria: "+arrayIds[position]+" idCidade: "+idcidade, Toast.LENGTH_SHORT).show();
				
				if(controller.temSubCategorias(idCategoria))
				{
					Toast.makeText(getApplicationContext(),"Com SubCategorias", Toast.LENGTH_SHORT).show();
					
					//SubCategory_selector;
					Intent i4= new Intent(getApplicationContext(),Select_SubCategory.class);
					i4.putExtra("idcidade",idcidade);
					i4.putExtra("idcategoria",idCategoria);
		    		startActivity(i4);
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Sem SubCategorias", Toast.LENGTH_SHORT).show();
					
					Intent i4= new Intent(getApplicationContext(),ActivityListViewLugar.class);
					i4.putExtra("idcidade",idcidade);
					i4.putExtra("idcategoria",idCategoria);
		    		startActivity(i4);
					
				}
				
				
			}
		});
		
	}
	

		
	
}



	


	

