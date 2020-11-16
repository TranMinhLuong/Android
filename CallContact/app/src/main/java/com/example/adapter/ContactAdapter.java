package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.callcontact.R;
import com.example.model.Contact;


import java.util.List;

import static com.example.callcontact.R.id.btnsms;

public class ContactAdapter extends ArrayAdapter<Contact> {
    @NonNull
    Activity context;
    int resource;
    @NonNull List<Contact> objects;
    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
        View row = inflater.inflate(this.resource, null);

        TextView txtname =(TextView) row.findViewById(R.id.txtname);
        TextView txtphone =(TextView) row.findViewById(R.id.txtphone);
        ImageButton btncall =(ImageButton) row.findViewById(R.id.btncall);
        ImageButton btnsms =(ImageButton) row.findViewById(R.id.btnsms);

        //trả về danh bạ hiện tại
        final Contact contact = this.objects.get(position);
        txtname.setText(contact.getName());
        txtphone.setText(contact.getPhone());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlingCall(contact);
            }
        });

        return row;
    }

    private void handlingCall(Contact contact) {
        Toast.makeText(this.context, "Bạn chọn: "+contact.getName(), Toast.LENGTH_LONG).show();
    }
}

/*public class ContactAdapter extends BaseAdapter {
    private  List<Contact> mListContact;
    private LayoutInflater mlLayoutInflater;
    private Context mContext;

    public ContactAdapter(Context context, List<Contact> contactList){
        mListContact = contactList;
        mlLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListContact.size();
    }

    @Override
    public Contact getItem(int position) {
        return mListContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = mlLayoutInflater.inflate(R.layout.contact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = (TextView) convertView.findViewById(R.id.txtname);
            viewHolder.tvphone = (TextView) convertView.findViewById(R.id.txtphone);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Contact contact = mListContact.get(position);

        viewHolder.tvname.setText(contact.getName());
        viewHolder.tvphone.setText(contact.getPhone());

        return convertView;
    }

    private class ViewHolder {
        TextView tvname;
        TextView tvphone;
    }
}*/
