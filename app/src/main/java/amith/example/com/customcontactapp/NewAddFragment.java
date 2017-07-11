package amith.example.com.customcontactapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by amith on 7/11/17.
 */

public class NewAddFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_add, container,false);
        final RelativeLayout addNewContactView = (RelativeLayout)v.findViewById(R.id.addNewContactView);
        Button save = (Button)v.findViewById(R.id.saveNewContact);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewContactView.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }
}
