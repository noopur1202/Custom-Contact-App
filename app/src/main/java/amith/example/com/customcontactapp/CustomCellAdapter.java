package amith.example.com.customcontactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by amith on 7/11/17.
 */

public class CustomCellAdapter extends BaseAdapter {

    private Context mContext;
    private final List<String> name;
    private final List<String> number;

    public CustomCellAdapter(Context c, List<String> name, List<String> number) {
        mContext = c;
        this.name = name;
        this.number = number;
    }


    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null==convertView){
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.single_contact_layout,null);
//            ImageView contactPhoto = (ImageView)convertView.findViewById(R.id.contactPhoto);
//            TextView contactName = (TextView)convertView.findViewById(R.id.contactName);
//            TextView contactNumber = (TextView)convertView.findViewById(R.id.contactNumber);
//            contactPhoto.setImageResource(R.drawable.options);
//            contactName.setText("Android");
//            contactNumber.setText("9999999999");
        }
        else {
            grid = (View)convertView;
        }
        return grid;
    }
}
