package amith.example.com.customcontactapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by amith on 7/12/17.
 */

public class MyCursorAdapter extends SimpleCursorAdapter {
    private Context ctx;

    public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.ctx = context;

    }

    @Override
    public View newView(final Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_contact_layout, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        ImageView moreArrow = (ImageView) view.findViewById(R.id.moreArrow);
        RelativeLayout contactInfoView = (RelativeLayout)view.findViewById(R.id.contactInfoView);

        moreArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callDetailsIntent= new Intent(ctx, CallDetailsActivity.class);
                ctx.startActivity(callDetailsIntent);
            }
        });

        contactInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactDetailsIntent= new Intent(ctx, ContactDetails.class);
                TextView name = (TextView)v.findViewById(R.id.contactName);
                String cName = name.getText().toString();
                contactDetailsIntent.putExtra("Contact Name",cName);
                ctx.startActivity(contactDetailsIntent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
