package cvturismo.gallery;

import br.exemplosocialoauth.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GalleryMainActivity extends Activity {

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

   
}