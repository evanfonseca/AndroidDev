package cvturismo.gallery;

import br.exemplosocialoauth.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {
   private Context mContext;

   // Constructor
   public GalleryImageAdapter(Context c) {
      mContext = c;
   }

   public int getCount() {
      return mThumbIds.length;
   }

   public Object getItem(int position) {
      return null;
   }

   public long getItemId(int position) {
      return 0;
   }

   // create a new ImageView for each item referenced by the Adapter
   public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView;
      if (convertView == null) {
      imageView = new ImageView(mContext);
      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
      } else {
      imageView = (ImageView) convertView;
      }

      imageView.setImageResource(mThumbIds[position]);
      return imageView;
   }

   // Keep all Images in array
   public Integer[] mThumbIds = {
      R.drawable.natureimage1, R.drawable.natureimage3,
      R.drawable.natureimage2, R.drawable.natureimage15,
      R.drawable.natureimage4, R.drawable.natureimage5,
      R.drawable.natureimage6, R.drawable.natureimage7,
      R.drawable.natureimage8, R.drawable.natureimage9,
      R.drawable.natureimage10, R.drawable.natureimage12,
      R.drawable.natureimage13, R.drawable.natureimage14,
      R.drawable.natureimage15, R.drawable.natureimage1,
      R.drawable.natureimage2, R.drawable.natureimage3,
      R.drawable.natureimage4, R.drawable.natureimage5,
      R.drawable.natureimage7, R.drawable.natureimage6
   };
}