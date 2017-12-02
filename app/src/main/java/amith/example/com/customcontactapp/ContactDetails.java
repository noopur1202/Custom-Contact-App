package amith.example.com.customcontactapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by amith on 7/13/17.
 */

public class ContactDetails extends Activity {
    Cursor cursor;
    MyCursorAdapter myCursorAdapter;
    String CName;
    Button call,sms,whatsapp;
    TextView phone, name, email;
    CallBroadcastManager callBroadcastManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);

        Intent intent = getIntent();
        CName = intent.getStringExtra("Contact Name");

        name = (TextView) findViewById(R.id.name);
        name.setText(CName);
        String cursorEmail = getEmail(CName);

        email = (TextView) findViewById(R.id.email);
        email.setText(cursorEmail);

        phone = (TextView) findViewById(R.id.phone);
        String cursorPhone = getPhone(CName);
        phone.setText(cursorPhone);

        call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = (String) phone.getText();
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:" + phoneNumber));

                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(ContactDetails.this,
                        Manifest.permission.CALL_PHONE)) {
                    return;
                }
                startActivity(phoneIntent);
                callBroadcastManager = new CallBroadcastManager();
                registerReceiver(callBroadcastManager,new IntentFilter(Intent.ACTION_MAIN));
            }
        });

        sms = (Button)findViewById(R.id.sms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = (String) phone.getText();
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(smsIntent);
            }
        });

        whatsapp = (Button)findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = (String) phone.getText();
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent whatsappIntent = new Intent(Intent.ACTION_SENDTO,uri);
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Check");
                whatsappIntent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(whatsappIntent, ""));
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentFilter intentFilter = new IntentFilter(
//                "android.intent.action.MAIN");
//
//        callBroadcastManager = new CallBroadcastManager();
//        registerReceiver(callBroadcastManager,intentFilter);
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        this.unregisterReceiver(this.callBroadcastManager);
//    }

    public String getEmail(String contactName) {

        String emailStr= "";
        final String[] projection = new String[]{ContactsContract.CommonDataKinds.Email.DATA,ContactsContract.CommonDataKinds.Email.TYPE};

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, projection,
                ContactsContract.Data.DISPLAY_NAME + "=?", new String[]{contactName}, null);

        if (cursor.moveToFirst()) {
            final int contactEmailColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);

            while (!cursor.isAfterLast()) {
                emailStr = emailStr + cursor.getString(contactEmailColumnIndex);
                cursor.moveToNext();
            }
        }
        return emailStr;
    }

    public String getPhone(String contactName) {

        String phoneStr= "";
        final String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DATA,ContactsContract.CommonDataKinds.Phone.TYPE};

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
                ContactsContract.Data.DISPLAY_NAME + "=?", new String[]{contactName}, null);

        if (cursor.moveToFirst()) {
            final int contactEmailColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);

            while (!cursor.isAfterLast()) {
                phoneStr = phoneStr + cursor.getString(contactEmailColumnIndex);
                cursor.moveToNext();
            }
        }
        return phoneStr;
    }

    public void previousActivity(View view){
        this.finish();
    }
}