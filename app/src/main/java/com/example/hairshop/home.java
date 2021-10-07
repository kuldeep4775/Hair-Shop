package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        BottomNavigationView bottomNavigationView = findViewById(R.id.navibar);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.item1:
                        return true;

                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(),barbers.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(),book_it.class));

                        overridePendingTransition(0,0);
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
    }


}
