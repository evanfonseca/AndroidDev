package cvturismo.principal;

import java.util.ArrayList;

import br.exemplosocialoauth.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GotoAdapter extends ArrayAdapter<String>{
	
	
	private final String[] itemname;
	private final Activity context;
	private final Integer[] imgid;

	public GotoAdapter(Activity context, String[] itemname, Integer[] imgid) {
		super(context, R.layout.lista_item_categoria, itemname);
		this.context=context;
		this.itemname=itemname;
		this.imgid=imgid;
	}
	
	@Override
    public View getView(int position,View view,ViewGroup parent)
    {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.lista_item_categoria, null,true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.tvCategoria);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.ivCategoria);
		TextView extratxt = (TextView) rowView.findViewById(R.id.btn_ver_number);
		
		txtTitle.setText(itemname[position]);
		imageView.setImageResource(imgid[position]);
		extratxt.setText("Description "+itemname[position]);
		
		return rowView;
		
    }

	

}
