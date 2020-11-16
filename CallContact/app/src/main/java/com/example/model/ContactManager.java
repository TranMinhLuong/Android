package com.example.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

///*public class ContactManager {
//    private Context mcontext;
//    private List<Contact> contactList;
//
//    public ContactManager(Context context){
//        mcontext = context;
//        getContactData();
//        Collections.sort(contactList);
//    }
//
//    private void getContactData() {
//        contactList = new ArrayList<>();
//
//        String[] projection = {
//                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Phone.NUMBER
//        };
//
//        Cursor phones = mcontext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null,null,null);
//
//        int nameIndex = phones.getColumnIndex(projection[0]);
//        int phoneIndex = phones.getColumnIndex(projection[1]);
//        phones.moveToFirst();
//
//        while (phones.moveToNext()){
//            String name = phones.getString(nameIndex);
//            String phone = phones.getString(phoneIndex);
//            int random = 1;
//            contactList.add(new Contact(random++,name, phone));
//        }
//        phones.close();
//    }
//
//}*/
