package cvturismo.principal;


import android.os.Bundle;  
import android.app.Activity;  
import android.content.Intent;
import android.view.Menu;  
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import cvturismo.R;

 
public class MainActivity extends Activity {  
    
    
	 @Override 
    protected void onCreate(Bundle savedInstanceState) {  
		 
       super.onCreate(savedInstanceState);  
        
       setContentView(R.layout.main);  
    
       final LinearLayout cidade=(LinearLayout) this.findViewById(R.id.cidade_button);
       final LinearLayout lugar=(LinearLayout) this.findViewById(R.id.place_button);
       final LinearLayout galeria=(LinearLayout) this.findViewById(R.id.gallery_button);
       final LinearLayout mapa=(LinearLayout) this.findViewById(R.id.map_button);
       final LinearLayout info=(LinearLayout) this.findViewById(R.id.info_button);
       final LinearLayout favorito=(LinearLayout) this.findViewById(R.id.favorites_button);
       final LinearLayout evento=(LinearLayout) this.findViewById(R.id.event_button);
       final LinearLayout definicao=(LinearLayout) this.findViewById(R.id.definition_button);

       
       cidade.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			Intent i= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		
			startActivity(i);

             
			// TODO Auto-generated method stub
			
		   }
	   });
       
       
       lugar.setOnClickListener(new View.OnClickListener() {
   		
   		@Override
   		public void onClick(View v) {
   			
   			

			Intent i1= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		
			startActivity(i1);
     
   			// TODO Auto-generated method stub
   			
   		}
   	}); 
      
       galeria.setOnClickListener(new View.OnClickListener() {
   		
   		@Override
   		public void onClick(View v) {
   			

			Intent i2= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		
			startActivity(i2);
                
   			// TODO Auto-generated method stub
   			
   		}
   	});
       
       
     mapa.setOnClickListener(new View.OnClickListener() {
   		
   		@Override
   		public void onClick(View v) {

			Intent i3= new Intent(getApplicationContext(),AndroidListViewActivity.class);
		
			startActivity(i3);
                
   			// TODO Auto-generated method stub
   			
   		  }
      	});
       
       info.setOnClickListener(new View.OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {

    			Intent i4= new Intent(getApplicationContext(),AndroidListViewActivity.class);
    		
    			startActivity(i4);
                   
      			// TODO Auto-generated method stub
      			
      		}
      	}); 
       

    
       favorito.setOnClickListener(new View.OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {

    			Intent i5= new Intent(getApplicationContext(),AndroidListViewActivity.class);
    		
    			startActivity(i5);
                   
      			// TODO Auto-generated method stub
      			
      		}
      	}); 
       
      evento.setOnClickListener(new View.OnClickListener() {
     		
     		@Override
     		public void onClick(View v) {

   			Intent i6= new Intent(getApplicationContext(),AndroidListViewActivity.class);
   		
   			startActivity(i6);
                  
     			// TODO Auto-generated method stub
     			
     		}
     	}); 
      
      definicao.setOnClickListener(new View.OnClickListener() {
   		
   		@Override
   		public void onClick(View v) {

 			Intent i7= new Intent(getApplicationContext(),cvturismo.definicoes.Definition.class);
 		
 			startActivity(i7);
                
   			// TODO Auto-generated method stub
   			
   		}
   	}); 
	 } 
	 
	
	
   
   
   
}
       