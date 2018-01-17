package com.apps.abjs.contactsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ajuna on 8/1/2017.
 */

public class ContactAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Contact> contactlist;
    private int resourse;
    Context context;


    public ContactAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contact> objects) {
//        Log.d("ListAdapter", objects.toString());

        contactlist = objects;
        this.context= context;
        this.resourse = resource;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);



    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("Hello", "Hello");
        Viewholder holder = null;
        if(convertView == null)
        {
            holder = new Viewholder();

            convertView = inflater.inflate(resourse,null);
            holder.name= (TextView)convertView.findViewById(R.id.name);
            holder.number= (TextView)convertView.findViewById(R.id.number);
            convertView.setTag(holder);

        }
        else {
            holder =(Viewholder)convertView.getTag();

        }

        holder.name.setText(contactlist.get(position).getName());
        Log.d("name",contactlist.get(position).getName());
        holder.number.setText(contactlist.get(position).getNumber());
        return convertView;
    }



    class Viewholder{
        private TextView name;
        private TextView number;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
