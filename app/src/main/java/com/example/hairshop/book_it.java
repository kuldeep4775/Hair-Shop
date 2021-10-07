package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hairshop.details;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

public class book_it extends AppCompatActivity {

    EditText Firstname,Mo,Email;
    Button btn;
    Spinner tim,select1;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_it);

        Firstname = findViewById(R.id.FN);
        Mo = findViewById(R.id.MN);
        Email = findViewById(R.id.EA);
        btn = findViewById(R.id.button2);
        tim = findViewById(R.id.tm);
        select1 = findViewById(R.id.selection);

        auth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navibar);

        bottomNavigationView.setSelectedItemId(R.id.item3);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(),home.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(),barbers.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item3:
                        return true;


                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(),gallery.class));

                        overridePendingTransition(0,0);
                        return true;


                    case R.id.item5:
                        startActivity(new Intent(getApplicationContext(),More.class));

                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference("Appoinment");

                //get the veluse
                String firstname = Firstname.getText().toString();
                String mo = Mo.getText().toString();
                String email = Email.getText().toString();
                String time = tim.getSelectedItem().toString();
                String select = select1.getSelectedItem().toString();

                details details = new details(firstname,mo,email,time,select);

                databaseReference.child(mo).setValue(details);

                Toast.makeText(book_it.this,"Data Inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
