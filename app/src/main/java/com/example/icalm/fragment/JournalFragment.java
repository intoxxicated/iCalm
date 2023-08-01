package com.example.icalm.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.icalm.Adapter.NoteAdapter;
import com.example.icalm.DataClass.Note;
import com.example.icalm.R;
import com.example.icalm.databinding.FragmentNotesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JournalFragment extends Fragment {
    FragmentNotesBinding binding;
    DatabaseReference notesReference;
    List<Note> notesList = new ArrayList<>();
    NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentNotesBinding.inflate(inflater,container,false);
        binding.addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new EditNotesFragment());
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        notesReference = firebaseDatabase.getReference("notes");
        readNotesFromFirebase();
        noteAdapter = new NoteAdapter(getActivity(), android.R.layout.simple_list_item_2, notesList);
        binding.listViewNotes.setAdapter(noteAdapter);
        return binding.getRoot();
    }

    public void readNotesFromFirebase() {
        notesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);
                    Log.e("note", ""+note);
                    notesList.add(note);
                }
                noteAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", "Failed to read notes", databaseError.toException());
            }
        });
    }


    public void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the existing fragment with the new fragment
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }

}
