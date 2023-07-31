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
import com.example.icalm.grid.GridAdapter;
import com.example.icalm.grid.GridItem;

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
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // Set the number of columns here (3 in this example)
        itemList = getDummyData(); // Replace getDummyData() with your actual data source
        gridAdapter = new GridAdapter(itemList);
        binding.recyclerView.setAdapter(gridAdapter);
//        binding.recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // int id=gridAdapter.getItemId();
//
//            }
//        });
        return binding.getRoot();
    }

    // Replace this method with your actual data source implementation
    private List<GridItem> getDummyData() {
        List<GridItem> dummyData = new ArrayList<>();
        // Add dummy data for grid items (image resource ID and text)
        dummyData.add(new GridItem(R.drawable.goal, "Goal"));
        dummyData.add(new GridItem(R.drawable.read, "Read"));
        dummyData.add(new GridItem(R.drawable.doctor, "Doctor"));
        dummyData.add(new GridItem(R.drawable.yoga, "Yoga"));
        dummyData.add(new GridItem(R.drawable.img_journal, "Journal"));
        dummyData.add(new GridItem(R.drawable.alarm, "Time Management"));
        // Add more items as needed
        return dummyData;
    }
}

