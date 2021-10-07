package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class database extends AppCompatActivity {

    ListView mylist;
    List<details> detailsList;

    DatabaseReference dfr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mylist = findViewById(R.id.list1);
        detailsList = new ArrayList<>();

        dfr = FirebaseDatabase.getInstance().getReference("Appoinment");

        dfr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                detailsList.clear();

                for (DataSnapshot custdatasnapshot : snapshot.getChildren()){
                    details details = custdatasnapshot.getValue(details.class);
                    detailsList.add(details);

                }
                ListAdepter adepter = new ListAdepter(database.this,detailsList);
                mylist.setAdapter(adepter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}