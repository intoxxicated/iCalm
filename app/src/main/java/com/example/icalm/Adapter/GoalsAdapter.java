package com.example.icalm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.DataClass.Goal;
import com.example.icalm.R;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {

    private List<Goal> goals;

    public GoalsAdapter(List<Goal> goals) {
        this.goals = goals;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Goal goal = goals.get(position);
        holder.goalTitleTextView.setText(goal.getTitle());
        holder.goalDescriptionTextView.setText(goal.getDescription());
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    static class GoalViewHolder extends RecyclerView.ViewHolder {
        TextView goalTitleTextView;
        TextView goalDescriptionTextView;

        GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goalTitleTextView = itemView.findViewById(R.id.goalTitleTextView);
            goalDescriptionTextView = itemView.findViewById(R.id.goalDescriptionTextView);
        }
    }
}

