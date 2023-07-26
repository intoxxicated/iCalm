package com.example.icalm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icalm.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ActivityRegisterBinding binding;
    boolean result;
    String email, pass;

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
                result=validate();
                if(result)
                {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();

                        }
                    });
                }

                }


        });
    }
    private boolean validate()
    {
        if(binding.etName.length()==0) {
            Toast.makeText(RegisterActivity.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
            return false ;
        }
        if(binding.etEmail.length()==0) {
            Toast.makeText(RegisterActivity.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
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

}