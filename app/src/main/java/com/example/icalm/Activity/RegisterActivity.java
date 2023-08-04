package com.example.icalm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.icalm.DataClass.User;
import com.example.icalm.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;

    User user;

    ActivityRegisterBinding binding;
    boolean result;
    String email, pass,name,phone,uid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();


        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email=binding.etEmail.getText().toString();
               pass=binding.etPassword.getText().toString();
                name=binding.etName.getText().toString();
                phone=binding.etPhone.getText().toString();
                user = new User("", name, phone,email,pass,"","","");
                result=validate();


                if(result)
                {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                uid=auth.getCurrentUser().getUid();
                               User user = new User(uid,name,phone, email,pass,"","","");
                                databaseReference.child("users").child(uid).setValue(user);



                                Toast.makeText(RegisterActivity.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }

                }
        });



    }






    private boolean validate()
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\- ]+$");
        Matcher matcher = pattern.matcher(name);
        if(binding.etName.length()==0) {
            Toast.makeText(RegisterActivity.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
            return false ;
        }
        if (!matcher.matches()) {
            Toast.makeText(RegisterActivity.this, "Only Alphabets Allowed", Toast.LENGTH_LONG).show();
            return false ;
        }
        if(binding.etEmail.length()==0) {
            Toast.makeText(RegisterActivity.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!isValidEmail(binding.etEmail.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Enter valid email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(binding.etPhone.length()!=10) {
            Toast.makeText(RegisterActivity.this, "Invalid phone number", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.etPassword.length()<8)
        {
            Toast.makeText(RegisterActivity.this, "Password length must be atleast 8", Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



}