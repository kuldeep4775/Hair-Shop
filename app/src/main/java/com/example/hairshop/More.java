package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class More extends AppCompatActivity {

    Button Log;
    ImageView pic;
    TextView Fname,Email;
    ImageButton imbtn;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Log = findViewById(R.id.log);
        pic = findViewById(R.id.pic);
        Fname = findViewById(R.id.fname);
        Email = findViewById(R.id.email);
        imbtn = findViewById(R.id.imageButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(More.this, database.class);
                startActivity(i);
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Fname.setText(personName);
            Email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.navibar);

        bottomNavigationView.setSelectedItemId(R.id.item5);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(), barbers.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(), book_it.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.item5:
                        return true;

                }
                return false;
            }
        });
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(More.this,"signout",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent i = new Intent(More.this, Login.class);
                        startActivity(i);
                    }
                });
    }

}
