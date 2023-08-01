package com.example.icalm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.icalm.DataClass.Note;
import com.example.icalm.R;
import com.example.icalm.databinding.EditNotesBinding;
import com.example.icalm.databinding.ListItemNoteBinding;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    ListItemNoteBinding binding;
    Context context;
    int resource;
    List<Note> objects;


    public NoteAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        binding=ListItemNoteBinding.inflate(LayoutInflater.from(context));
        Note note = objects.get(position);


        binding.textViewNoteContent.setText(note.getId().toString().trim());

        return binding.getRoot();

    }

    @Override
    public int getCount() {

        return objects.size();
    }
}
