package cvturismo.principal;

import java.util.ArrayList;
import java.util.HashMap;

import br.exemplosocialoauth.R;
import cvturismo.cidade.parsers.DBController;
import cvturismo.cidade.parsers.ActivityListViewLugar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Select_SubCategory extends ListActivity{
	
	ListView list;
	String[] itemname ;
	Integer[] imgid={
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip,
			R.drawable.btn_comer_clip
	};
	
	DBController controller;
	ArrayList<HashMap<String, String>> listSubCat;
	Integer[] arrayIds;
	
	int idcidade, idcategoria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorias);
		
		Intent i1 = getIntent();  
		idcidade = i1.getIntExtra("idcidade",0);
		idcategoria=i1.getIntExtra("idcategoria",0);
		controller = new DBController(this);
		listSubCat=controller.getAllSubCategoriasByIdCategoria(idcategoria);
		
		
		ArrayList<String> listString = new ArrayList<String>();
		ArrayList<Object> listInt = new ArrayList<Object>();
		
		int x;
        for(HashMap<String,String> row : listSubCat){
            listString.add(row.get("Descricao"));
            
            x= Integer.parseInt(row.get("Id"));
            listInt.add(x);
        }
		
        itemname = listString.toArray(new String[listString.size()]);
        arrayIds = (Integer[]) listInt.toArray(new Integer[listInt.size()]);

        GotoAdapter adapter=new GotoAdapter(this, itemname, imgid);
		list=(ListView)findViewById(android.R.id.list);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//String Slecteditem= itemname[position];
				int idsubcategoria = arrayIds[position];
				
				Toast.makeText(getApplicationContext()," idCategoria: "+arrayIds[position]+" idCidade: "+idcidade, Toast.LENGTH_SHORT).show();
				
				Intent i4= new Intent(getApplicationContext(),ActivityListViewLugar.class);
				i4.putExtra("idcidade",idcidade);
				i4.putExtra("idcategoria",idcategoria);
				i4.putExtra("idsubcategoria",idsubcategoria);
	    		startActivity(i4);
				
				
			}
		});
		
	
		
	}
	
	
	

}
