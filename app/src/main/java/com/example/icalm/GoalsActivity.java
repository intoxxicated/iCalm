package com.example.icalm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.Adapter.GoalsAdapter;
import com.example.icalm.DataClass.Goal;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    private RecyclerView goalsRecyclerView;
    private GoalsAdapter goalsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        goalsRecyclerView = findViewById(R.id.goalsRecyclerView);
        goalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Goal> goals = createDummyGoals();
        goalsAdapter = new GoalsAdapter(goals);
        goalsRecyclerView.setAdapter(goalsAdapter);
    }

    private List<Goal> createDummyGoals() {
        List<Goal> goals = new ArrayList<>();
        goals.add(new Goal("Self Awareness","Meditation encourages self-reflection and self-exploration, leading to a better understanding of your beliefs, values, and behaviors." ));
        goals.add(new Goal("stress reduction", "Meditation is often used to manage stress and promote relaxation. Regular practice can help reduce the body's stress response, leading to a calmer and more centered state of mind"));
        goals.add(new Goal("inner peace", "Meditation can lead to a sense of inner peace and harmony, even amid the chaos of daily life."));
        goals.add(new Goal("positive_outlook", "Meditation can contribute to a more positive and optimistic mindset, reducing negative thought patterns and promoting a sense of gratitude."));
        goals.add(new Goal("personal growth", "Many people use meditation as a tool for personal growth, seeking to evolve mentally, emotionally, and spiritually."));
        goals.add(new Goal("spiritual growth", "For those with a spiritual orientation, meditation can be a means of deepening their connection to a higher power or exploring existential questions."));
        goals.add(new Goal("reduced anxiety", "Regular meditation practice has been linked to reduced symptoms of anxiety disorders and can provide a sense of inner calm and tranquility."));
        goals.add(new Goal("emotional regulations", "Meditation can aid in managing emotions by enhancing emotional awareness and promoting a balanced and less reactive response to challenging situations."));
        goals.add(new Goal("better sleep", "Meditation can promote relaxation and reduce insomnia by calming the mind and inducing a state of relaxation conducive to sleep."));
        goals.add(new Goal("improved concentration", "Certain meditation techniques, such as focused attention meditation, aim to enhance concentration and attention span. This can be beneficial for tasks that require sustained focus."));
        goals.add(new Goal("mindfulness", "Mindfulness meditation involves cultivating awareness of the present moment without judgment. It can help you develop a deeper understanding of your thoughts, emotions, and sensations."));
        return goals;
    }
}
