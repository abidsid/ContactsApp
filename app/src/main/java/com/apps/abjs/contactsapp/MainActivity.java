package com.apps.abjs.contactsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public TextView outputtext;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.listview);

        outputtext = (TextView)findViewById(R.id.textView1);
        fetchContacts();

    }
    public void fetchContacts(){
        String phn = null;
        Uri contnet_uri = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String display_name=  ContactsContract.Contacts.DISPLAY_NAME;
        ContactAdapter contactAdapter;

        Uri phone_uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER =ContactsContract.CommonDataKinds.Phone.NUMBER;

        StringBuffer output = new StringBuffer();


        ContentResolver contentResolver = getContentResolver();
        List<Contact> list = new ArrayList<>();

        Cursor cursor = contentResolver.query(contnet_uri, null,null, null, null);
//        String outputnew = output.toString();


        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Contact contact = new Contact();
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(display_name));
                output.append("\n First Name:" + name);
                contact.setName(name);
                Cursor phoneCursor = contentResolver.query(phone_uri, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
                while (phoneCursor.moveToNext()) {
                    phn = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                    output.append("\n Phone number:" + phn);

                }
                contact.setNumber(phn);
                phoneCursor.close();
                output.append("\n");
                list.add(contact);

            }
//            Log.d("list", list.toString());

            contactAdapter = new ContactAdapter(this,R.layout.row_view,list);
            Log.d("list", contactAdapter.toString());
            listView.setAdapter(contactAdapter);

//            outputtext.setText(output);



            }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
