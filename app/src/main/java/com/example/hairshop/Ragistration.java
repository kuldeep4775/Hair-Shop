package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairshop.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class Ragistration extends AppCompatActivity {


    EditText firstname,Mo,Email,Pass;
    Button btn;

    FirebaseDatabase database;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragistration);
        getSupportActionBar().hide();

        firstname = findViewById(R.id.FN);
        Mo = findViewById(R.id.MN);
        Email = findViewById(R.id.EA);
        Pass = findViewById(R.id.Pass);
        btn = findViewById(R.id.button2);


        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Ragistration.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                auth.createUserWithEmailAndPassword(Email.getText().toString(), Pass.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            database = FirebaseDatabase.getInstance();
                            databaseReference = database.getReference("Users");

                            //Users user = new Users(firstname.getText().toString(),Mo.getText().toString(),Email.getText().toString(),Pass.getText().toString());

                            String Firstname = firstname.getText().toString();
                            String mo = Mo.getText().toString();
                            String email = Email.getText().toString();
                            String pass = Pass.getText().toString();

                            Users users = new Users(Firstname,mo,email,pass);

                            databaseReference.child(mo).setValue(users);


                            Toast.makeText(Ragistration.this,"User created success",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Ragistration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ValidateName() | !ValidateMo() | !ValidateEmail() | !ValidatePass() | !ValidateCpass())
                {
                    return;
                }
                progressDialog.show();


                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference("Users");

                //get the veluse
                String firstname = Firstname.getText().toString();
                String mo = Mo.getText().toString();
                String email = Email.getText().toString();
                String pass = Pass.getText().toString();
                String cpass = Cpass.getText().toString();

                Users users = new Users(firstname,mo,email,pass,cpass);

                databaseReference.child(mo).setValue(users);
                progressDialog.dismiss();
            }
        });*/


    }

    /*// Validation
    private Boolean ValidateName(){
        String Val = Firstname.getText().toString();
        if(Val.isEmpty()){
            Firstname.setError("Field cannot be empty");
            return true;
        }
        else {
            Firstname.setError(null);
            return false;
        }
    }

    private Boolean ValidateMo(){
        String Val = Mo.getText().toString();
        if(Val.isEmpty()){
            Mo.setError("Field cannot be empty");
            return true;
        }
        else {
            Mo.setError(null);
            return false;
        }
    }

    private Boolean ValidateEmail(){
        String Val = Email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(Val.isEmpty()){
            Email.setError("Field cannot be empty");
            return false;
        }
        else if(!Val.matches(emailPattern)){
            Email.setError("Invalid Email Address");
            return false;
        }
        else {
            Email.setError(null);
            return true;
        }
    }

    private Boolean ValidatePass(){
        String Val = Pass.getText().toString();
        String passwordval = "(?=.*[a-zA-Z])"+"(?=.*[0-9])" + "(?=.*[@#$%^&+=])";


        if(Val.isEmpty()){
            Pass.setError("Field cannot be empty");
            return true;
        }else if(!Val.matches(passwordval)){
            Pass.setError("Password is too weak");
            return false;
        } else {
            Pass.setError(null);
            return false;
        }
    }

    private Boolean ValidateCpass() {
        String Val = Cpass.getText().toString();
        String passwordval = "(?=.*[a-zA-Z])"+"(?=.*[0-9])" + "(?=.*[@#$%^&+=])";

        if (Val.isEmpty()) {
            Cpass.setError("Field cannot be empty");
            return true;
        } else if (!Val.matches(passwordval)) {
            Cpass.setError("Password is too weak");
            return false;
        } else {
            Cpass.setError(null);
            return false;
        }
    }*/

}
