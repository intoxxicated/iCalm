package com.example.icalm.fragment.Journal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.icalm.Adapter.NoteAdapter;
import com.example.icalm.DataClass.Note;
import com.example.icalm.R;
import com.example.icalm.databinding.EditNotesBinding;
import com.example.icalm.databinding.FragmentNotesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JournalFragment extends Fragment {
    FragmentNotesBinding binding;

    List<Note> notesList = new ArrayList<>();
    NoteAdapter noteAdapter;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference notesReference = firebaseDatabase.getReference("notes");
    AlertDialog dialog;

    Note position;

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


        readNotesFromFirebase();
        noteAdapter = new NoteAdapter(getActivity(), android.R.layout.simple_list_item_2, notesList);
        binding.listViewNotes.setAdapter(noteAdapter);

        binding.listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position=notesList.get(i);

                showAlertDialog();
            }
        });


        return binding.getRoot();

    }


    public void readNotesFromFirebase() {
        notesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);
                    notesList.add(note);
                    binding.shimmerViewContainer.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                }
                noteAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", "Failed to read notes", databaseError.toException());
                binding.shimmerViewContainer.stopShimmer();
                binding.shimmerLayout.setVisibility(View.GONE);
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

    private  void showAlertDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("What You Want To Do ? " )
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showUpdateDialog();

                        Toast.makeText(getActivity(), "UPDATE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteNode();
                        dialog.dismiss();

                    }
                });
        dialog=builder.create();
        dialog.show();


    }

    public void deleteNode()
    {
        String id=position.getKey();
        String title=position.getId();
        notesReference.child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "NOTE DELETED: "+title, Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "Error deleting Note:  " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void showUpdateDialog(){
        EditNotesBinding layout= EditNotesBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(layout.getRoot());
        layout.title.setText(position.getId());
        layout.editText.setText(position.getContent());

        layout.submit.setText("UPDATE");

        dialog=builder.create();
        Window window= dialog.getWindow();
        window.setLayout(200,200);
        dialog.show();
        layout.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newContent=layout.editText.getText().toString();
                String newId=layout.title.getText().toString();
                saveNoteToFirebase(newContent,newId);
            }
        });

    }
    public void saveNoteToFirebase(String content , String title) {
        String noteId = position.getKey();
        Note note = new Note(title, content,noteId);
        notesReference.child(noteId).setValue(note);
        dialog.dismiss();
    }

}
