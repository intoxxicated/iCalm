package com.example.icalm.fragment.Journal;

import android.content.Intent;
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

import com.example.icalm.DataClass.Note;
import com.example.icalm.databinding.EditNotesBinding;
import com.example.icalm.databinding.FragmentNotesBinding;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditNotesFragment extends Fragment {
    EditNotesBinding binding;
    DatabaseReference notesReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditNotesBinding.inflate(inflater, container, false);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        notesReference= firebaseDatabase.getReference("notes");
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteContent = binding.editText.getText().toString().trim();
                String noteTitle =binding.title.getText().toString().trim();
                if (!noteContent.isEmpty()) {
                    saveNoteToFirebase(noteContent, noteTitle);
                    binding.editText.setText("");
                    binding.title.setText("");
                    fragmentManager.popBackStack();
                } else {
                    Toast.makeText(getActivity(), "Please enter a note.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();

    }
    public void saveNoteToFirebase(String content , String title) {
        String noteId = notesReference.push().getKey();
        Note note = new Note(title, content,noteId);
        notesReference.child(noteId).setValue(note);
    }
}



