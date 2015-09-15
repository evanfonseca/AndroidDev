package cvturismo.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import br.exemplosocialoauth.R;




public class Galeria extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			  setContentView(R.layout.galeria_principal);
			  
			  
			  GridView gridview = (GridView) findViewById(R.id.gridview);
		      gridview.setAdapter(new GalleryImageAdapter(this));
		      
		      gridview.setOnItemClickListener(new OnItemClickListener() {
		          public void onItemClick(AdapterView<?> parent, View v,
		                  int position, long id) {
		        	  
		              // Send intent to SingleViewActivity
		              Intent i = new Intent(getApplicationContext(), GalleryActivity.class);
		              // Pass image index
		              i.putExtra("id", position);
		              startActivity(i);
		          }
		      });

		  
			  
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

		
}
