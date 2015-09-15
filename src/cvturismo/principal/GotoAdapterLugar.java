package cvturismo.principal;

import br.exemplosocialoauth.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GotoAdapterLugar extends ArrayAdapter<String>{
	
	private final String[] itemname;
	private final Activity context;
	private final Integer[] imgid;
	
	public GotoAdapterLugar(Activity context, String[] itemname, Integer[] imgid) {
		super(context, R.layout.lista_item_lugar, itemname);
		this.context=context;
		this.itemname=itemname;
		this.imgid=imgid;
	}
	
	
	@Override
    public View getView(int position,View view,ViewGroup parent)
    {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.lista_item_lugar, null,true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.tvLugar);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.ivLugar);
		TextView extratxt = (TextView) rowView.findViewById(R.id.desc_lugar);
		
		txtTitle.setText(itemname[position]);
		imageView.setImageResource(imgid[position]);
		extratxt.setText("Description "+itemname[position]);
		
		return rowView;
		
    }

}
