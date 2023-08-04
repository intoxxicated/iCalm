package com.example.icalm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.DataClass.MusicItem;
import com.example.icalm.R;
import com.example.icalm.VideoActivity;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<MusicItem> itemList;
    View view;
    String url;


    public MusicAdapter(List<MusicItem> itemList) {

        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MusicItem item = itemList.get(position);
        holder.linearLayout.setBackgroundResource(item.getImageResource());
        holder.textView.setText(item.getText());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCorresponding(item.getText());
            }
        });

    }


    private void openCorresponding(String text) {

        switch (text) {
            case "Morning":
                url="https://www.jango.com/music/Good+Morning";
                break;
            case "Bedtime":
                url="https://www.jango.com/stations/397867344";
                break;
            case "Nature":
                url="https://www.jango.com/music/Nature+Sounds";
                break;
            case "Meditation":
                url="https://www.jango.com/music/Meditation+Spa";
                break;
            case "Travel":
                url="https://www.jango.com/music/Music+Travel+Love";
                break;
            case "Yoga":
                url="https://www.jango.com/music/Yoga+Exercices+Club";
                break;
            case "Relax":
                url="https://www.jango.com/stations/363327752";
                break;
            case "Study":
                url="https://www.jango.com/stations/401932194";
                break;
            default:
                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();

        }
        Intent intent=new Intent(view.getContext(), VideoActivity.class);
        intent.putExtra("url",url);
        view.getContext().startActivity(intent);

    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
            linearLayout=itemView.findViewById(R.id.linerBG);
        }
    }


}
