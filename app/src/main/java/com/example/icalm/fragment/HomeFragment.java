package com.example.icalm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icalm.R;
import com.example.icalm.databinding.FragmentHomeBinding;
import com.example.icalm.Adapter.GridAdapter;
import com.example.icalm.GridItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    private GridAdapter gridAdapter;
    private List<GridItem> itemList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        itemList = getDummyData();
        gridAdapter = new GridAdapter(itemList);
        binding.recyclerView.setAdapter(gridAdapter);
        return binding.getRoot();

    }

    // Replace this method with your actual data source implementation
    private List<GridItem> getDummyData() {
        List<GridItem> dummyData = new ArrayList<>();
        dummyData.add(new GridItem(R.drawable.goal, "Goal"));
        dummyData.add(new GridItem(R.drawable.read, "Read"));
        dummyData.add(new GridItem(R.drawable.doctor, "Doctor"));
        dummyData.add(new GridItem(R.drawable.yoga, "Yoga"));
        dummyData.add(new GridItem(R.drawable.img_journal, "Journal"));
        dummyData.add(new GridItem(R.drawable.alarm, "Time Management"));
        return dummyData;
    }
}

