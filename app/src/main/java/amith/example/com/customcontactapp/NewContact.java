package amith.example.com.customcontactapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by amith on 7/12/17.
 */

public class NewContact extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);
    }

//    public void UpdateDataInProvider(){
//        Uri URI_CONTACT_DATA = ContactsContract.Data.CONTENT_URI;
//        String COLUMN_EMAIL = ContactsContract.CommonDataKinds.Email.ADDRESS;
//        String COLUMN_DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
//        String COLUMN_MIMETYPE = ContactsContract.Data.MIMETYPE;
//
//        String[] PROJECTION = {
//                COLUMN_DISPLAY_NAME,
//                COLUMN_EMAIL,
//                COLUMN_MIMETYPE
//        };
//
//        private Cursor getCursor() {
//            ContentResolver resolver = context.getContentResolver();
//            String selection = COLUMN_MIMETYPE + "=?";
//            final String[] selectionArgs = {ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE};
//            return resolver.query(URI_CONTACT_DATA, PROJECTION, selection, selectionArgs, null);
//        }
//    }



    public void previousActivity(View view){
        this.finish();
    }
}
