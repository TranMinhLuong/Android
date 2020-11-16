package com.example.callcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adapter.ContactAdapter;
import com.example.model.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvcontact;
    ArrayList<Contact> arrContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvcontact = (ListView) findViewById(R.id.lvcontact);
        arrContact = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }else{
            addControls();
        }

//        addControls();
        addEvents();
    }

    private void addControls() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int random = 0;
            arrContact.add(new Contact(random++, name, number));
            contactAdapter = new ContactAdapter(MainActivity.this, R.layout.contact, arrContact);
            lvcontact.setAdapter(contactAdapter);
            Collections.sort(arrContact);

        }
//        arrContact.add(new Contact(1, "Nguyen Van Teo", "00919181"));
//        contactAdapter = new ContactAdapter(MainActivity.this, R.layout.contact, arrContact);
//        lvcontact.setAdapter(contactAdapter);
    }

    private void addEvents() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                addControls();
            }
        }
    }
}

   /* public class MainActivity extends AppCompatActivity{
        private ListView listView;
        private ContactAdapter contactAdapter;
        private ContactManager contactManager;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            addControls();
        }

        private void addControls() {
            contactManager = new ContactManager(this);

            listView = (ListView) findViewById(R.id.lvcontact);
            contactAdapter = new ContactAdapter(this, null);
            listView.setAdapter(contactAdapter);

        }
    }*/
