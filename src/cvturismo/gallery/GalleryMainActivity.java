package cvturismo.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import br.exemplosocialoauth.R;
import cvturismo.definicoes.Definition;

public class GalleryMainActivity  extends Definition implements OnClickListener {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.gallery_activity_main);
      
      
      
      GridView gridview = (GridView) findViewById(R.id.gridview);
      gridview.setAdapter(new GalleryImageAdapter(this));
      
      gridview.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View v,
                  int position, long id) {
        	  
              // Send intent to SingleViewActivity
              Intent i = 
              new Intent(getApplicationContext(), GalleryActivity.class);
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