package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairshop.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText us;
    EditText pass;
    TextView msg;
    Button lo;
    GoogleSignInClient mGoogleSignInClient;

    private static int RC_SIGN_IN = 100;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        us = findViewById(R.id.EA);
        pass = findViewById(R.id.Pass);
        lo = findViewById(R.id.button2);


        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isuser();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

         // Check for existing Google Sign In account, if the user is already signed in
         // the GoogleSignInAccount will be non-null.
         GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

         // Set the dimensions of the sign-in button.
         SignInButton signInButton = findViewById(R.id.sign_in_button);
         signInButton.setSize(SignInButton.SIZE_STANDARD);

         signInButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 signIn();
             }
         });
    }



    public void Logiuser(View view){

        isuser();
    }

    private void isuser() {

        String userEnteredEmail = us.getText().toString().trim();
        String userEnterdPass = pass.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkuser = reference.orderByChild("us").equalTo(userEnteredEmail);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    us.setError(null);


                    String password = snapshot.child(userEnteredEmail).child("pass1").getValue(String.class);

                    if (password.equals(userEnterdPass)){

                        us.setError(null);


                        String Cpassword = snapshot.child(userEnteredEmail).child("cpass").getValue(String.class);
                        String useremail = snapshot.child(userEnteredEmail).child("email").getValue(String.class);
                        String username = snapshot.child(userEnteredEmail).child("firstname").getValue(String.class);
                        String usermo = snapshot.child(userEnteredEmail).child("mo").getValue(String.class);
                        String userpass = snapshot.child(userEnteredEmail).child("pass1").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),More.class);

                        intent.putExtra("cpass",Cpassword);
                        intent.putExtra("email",useremail);
                        intent.putExtra("firstname",username);
                        intent.putExtra("mo",usermo);
                        intent.putExtra("pass1",userpass);

                        startActivity(intent);
                    }
                    else {
                        pass.setError("wrong password");
                        pass.requestFocus();
                    }
                }else {
                    us.setError("No such user exit");
                    us.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                //String personGivenName = acct.getGivenName();
                //String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                //String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }
            startActivity(new Intent(Login.this,home.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

        }
    }

       public void signUp(View view) {
            startActivity( new Intent(getApplicationContext(),Ragistration.class));
        }

}
