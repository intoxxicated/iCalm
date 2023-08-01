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

import com.example.icalm.R;
import com.example.icalm.databinding.EditProfileBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class EditProfileFragment extends Fragment {
    EditProfileBinding binding;
    FirebaseStorage firebaseStorage;
    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditProfileBinding.inflate(inflater, container, false);
        firebaseStorage=FirebaseStorage.getInstance();
        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("check", "upload");
                uploadPhoto();
            }
        });
        return binding.getRoot();
    }

    private void uploadPhoto()
    {
        ImagePicker.with(getActivity())
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
//        binding.dp.setImageURI();
    }


    public void uploadImage(Uri uri) {
        binding.dp.setImageURI(uri);
        Log.e("here", "uploAD image");
        firebaseStorage.getReference()
                .child("dp")
                .putFile(uri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Picture Uploaded", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
