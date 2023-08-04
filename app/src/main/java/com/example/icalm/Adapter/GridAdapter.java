package com.example.icalm.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.DataClass.GridItem;
import com.example.icalm.fragment.Home.GoalsActivity;
import com.example.icalm.fragment.Home.ProgressActivity;
import com.example.icalm.R;
import com.example.icalm.fragment.Home.TipsActivity;
import com.example.icalm.fragment.Home.ebook.EbookActivity;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<GridItem> itemList;
    View view;


    public GridAdapter(List<GridItem> itemList) {

        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GridItem item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textView.setText(item.getText());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCorresponding(item.getText());
            }
        });

    }

    private void openCorresponding(String text) {

        switch (text){
            case "Doctor":
                searchYogaCenterOnMap();
                break;
            case "Read":

                Intent intent =new Intent(view.getContext(), EbookActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "Tips":

                Intent intent1 =new Intent(view.getContext(), TipsActivity.class);
                view.getContext().startActivity(intent1);
                break;
            case "Goal":
                Intent intent2 =new Intent(view.getContext(), GoalsActivity.class);
                view.getContext().startActivity(intent2);
                break;
            case "Progress Streak":
                Intent intent3 =new Intent(view.getContext(), ProgressActivity.class);
                view.getContext().startActivity(intent3);
                break;
            default:
                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
    public void searchYogaCenterOnMap() {
        String location = "geo:0,0?q=Psychiatrist+near+me";
        Uri gmmIntentUri = Uri.parse(location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Log.e("map", ""+mapIntent.getData());
        view.getContext().startActivity(mapIntent);
    }


}
