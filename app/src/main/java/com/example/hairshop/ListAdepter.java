package com.example.hairshop;

import android.app.Activity;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListAdepter extends ArrayAdapter {

    private Activity mconntext;
    List<details> custlist;
    //Button update;


    FirebaseDatabase firebaseDatabase;

    public ListAdepter(Activity mconntext,List<details> custlist) {
        super(mconntext, R.layout.list_item, custlist);
        this.mconntext = mconntext;
        this.custlist = custlist;

        /*update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });*/

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mconntext.getLayoutInflater();
        View listItems = inflater.inflate(R.layout.list_item,null,true);

        TextView Fn = listItems.findViewById(R.id.first);
        TextView Pho = listItems.findViewById(R.id.Mo);
        TextView email = listItems.findViewById(R.id.email2);
        TextView Time = listItems.findViewById(R.id.Time);
        TextView Select = listItems.findViewById(R.id.selection);

        details details = custlist.get(position);

        Fn.setText(details.getFirstname());
        Pho.setText(details.getMo());
        email.setText(details.getEmail2());
        Time.setText(details.getTime());
        Select.setText(details.getSelect());


        return  listItems;

    }

}
