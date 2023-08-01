package com.example.icalm.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.icalm.DataClass.Note;
import com.example.icalm.DataClass.User;
import com.example.icalm.R;
import com.example.icalm.databinding.EditProfileBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class EditProfileFragment extends Fragment {
    EditProfileBinding binding;
    String dpURL;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser getCurrentUser;
    FirebaseStorage firebaseStorage;
    public EditProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditProfileBinding.inflate(inflater, container, false);
        firebaseStorage= FirebaseStorage.getInstance();



        showProfile();

        binding.dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateProfile();
                showProfile();
            }
        });
        return binding.getRoot();
    }

    public void uploadPhoto() {
        ImagePicker.with(getActivity())
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();

    }


    public void uploadImage(Uri uri) {
        binding.dp.setImageURI(uri);

        firebaseStorage.getReference()
                .child("dp")
                .putFile(uri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Picture Uploaded", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public  void UpdateProfile()
    {

        String name=binding.etName.getText().toString();
        String email=binding.etEmail.getText().toString();
        String phone=binding.etPhone.getText().toString();
        String age=binding.etAge.getText().toString();
        String address=binding.etAddress.getText().toString();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        User user=new User();
        user.setName(name);
        user.setDob(age);
        user.setAddress(address);
        user.setPhone(phone);
        user.setEmail(email);
        databaseReference=firebaseDatabase.getReference().child("users").child(userId);
        databaseReference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showProfile() {


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase.getReference().child("users")
                        .child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the DataSnapshot exists and contains valid data

                    User user = snapshot.getValue(User.class);
                    binding.etName.setText(user.getName());
                    binding.name.setText(user.getName());
                    binding.etEmail.setText(user.getEmail());
                    binding.etPhone.setText(user.getPhone());
                    binding.etAddress.setText(user.getAddress());
                    binding.etAge.setText(user.getDob());


               }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors that occurred while reading from the database.
            }
        });
    }

}


