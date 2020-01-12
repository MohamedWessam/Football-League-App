package com.wessam.footballleagueapplication.ui.main.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.room.FavouriteDatabase;
import com.wessam.footballleagueapplication.data.db.room.FavouriteList;
import com.wessam.footballleagueapplication.databinding.FragmentFavouriteBinding;
import com.wessam.footballleagueapplication.ui.main.adapter.FavouriteAdapter;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);

        binding.favouriteRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        getFavouriteData();

        return binding.getRoot();
    }

    private void getFavouriteData() {
        List<FavouriteList> favoriteLists = FavouriteDatabase.getDatabase(getContext()).favoriteDao().getFavouriteTeams();

        FavouriteAdapter adapter = new FavouriteAdapter(getActivity(), favoriteLists);
        binding.favouriteRecyclerView.setAdapter(adapter);
    }

}