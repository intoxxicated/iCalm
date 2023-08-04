package com.example.icalm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.Adapter.MusicAdapter;
import com.example.icalm.DataClass.MusicItem;
import com.example.icalm.R;
import com.example.icalm.databinding.FragmentHomeBinding;
import com.example.icalm.Adapter.GridAdapter;
import com.example.icalm.DataClass.GridItem;
import com.example.icalm.databinding.FragmentMusicBinding;

import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {
    FragmentMusicBinding binding;

    private MusicAdapter musicAdapter;
    private List<MusicItem> itemList;

    public MusicFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMusicBinding.inflate(inflater,container,false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        itemList = getDummyData();
        musicAdapter = new MusicAdapter(itemList);
        binding.recyclerView.setAdapter(musicAdapter);
        return binding.getRoot();

    }

    // Replace this method with your actual data source implementation
    private List<MusicItem> getDummyData() {
        List<MusicItem> dummyData = new ArrayList<>();
        dummyData.add(new MusicItem(R.drawable.meditation, "Meditation"));
        dummyData.add(new MusicItem(R.drawable.yogas, "Yoga"));
        dummyData.add(new MusicItem(R.drawable.study, "Study"));
        dummyData.add(new MusicItem(R.drawable.bedtime, "Bedtime"));
        dummyData.add(new MusicItem(R.drawable.nature, "Nature"));
        dummyData.add(new MusicItem(R.drawable.morning, "Morning"));
        dummyData.add(new MusicItem(R.drawable.travel, "Travel"));
        dummyData.add(new MusicItem(R.drawable.calm, "Relax"));
        return dummyData;
    }
}

