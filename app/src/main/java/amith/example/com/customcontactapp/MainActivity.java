package amith.example.com.customcontactapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends ListActivity {

    ListView listview;
    Cursor cursor;
    ImageView menu;
    MyCursorAdapter myCursorAdapter;
    Button addContact;
    String name;
    String phonenumber;
    public List<String> nameList, phoneList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyContentObserver contentObserver = new MyContentObserver();
        getContactsList();

        menu = (ImageView)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(MainActivity.this, menu);
                popup.getMenuInflater().inflate(R.menu.toggle_menu, popup.getMenu());
                popup.show();
            }
        });

        addContact = (Button)findViewById(R.id.addContact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNew = new Intent(getApplicationContext(), NewContact.class);
                startActivity(addNew);
            }
        });

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int pos = parent.getPositionForView(view);
//            }
//        });
    }

    public void getContactsList(){
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        startManagingCursor(cursor);

        String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI, ContactsContract.CommonDataKinds.Phone._ID};

        int[] to = {R.id.contactName, R.id.contactNumber, R.id.contactPhoto};
        myCursorAdapter = (MyCursorAdapter) new MyCursorAdapter(this, R.layout.single_contact_layout, cursor, from, to);
        setListAdapter(myCursorAdapter);

        listview = getListView();
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private class MyContentObserver extends ContentObserver {

        public MyContentObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }

    }

//    public void getContactsIntoArrayList(){
//
//        nameList=new ArrayList<String>();
//        phoneList=new ArrayList<String>();
//
//        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
//        while (cursor.moveToNext()) {
//            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            nameList.add(name);
//            phoneList.add(phonenumber);
//        }
//        cursor.close();
//    }
}