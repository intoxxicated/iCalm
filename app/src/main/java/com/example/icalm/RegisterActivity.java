package com.example.icalm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.icalm.databinding.ActivityRegisterBinding;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    User user;

    ActivityRegisterBinding binding;
    boolean result;
    String email, pass,name,phone,uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("user");

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
                                FirebaseUser currentUser =auth.getCurrentUser();
                                uid=auth.getCurrentUser().getUid();
                                User user = new User(uid,name,phone, email,pass,"","","");
                                databaseReference.child("user").child(uid).setValue(user);


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
    private void emptyInputEditText() {
        binding.etName.setText(null);
        binding.etEmail.setText(null);
        binding.etPhone.setText(null);
        binding.etPassword.setText(null);
    }

    private  void  updateUserUI(FirebaseUser currentUser){
        database=FirebaseDatabase.getInstance();
      String keyID =databaseReference.push().getKey();
      databaseReference.child(keyID).setValue(user);

    }


}