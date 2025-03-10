package com.proyecto.mallnav.adapters.venues;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.proyecto.mallnav.R;
import com.proyecto.mallnav.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class VenueListAdapter extends VenuesListAdapterBase<VenueViewHolder> {

    private List<Venue> rawList     = new ArrayList<>();
    private List<Venue> currentList = new ArrayList<>();


    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_venue, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        Venue venue = currentList.get(position);
        holder.bind(venue);
    }

    @Override
    public int getItemCount() {
        return currentList.size();
    }

    public void clear() {
        rawList.clear();
        currentList.clear();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String query) {
        if (TextUtils.isEmpty(query)) {
            currentList.clear();
            currentList.addAll(rawList);
        } else {
            currentList.clear();
            for (Venue venue : rawList) {
                if (venue.getNombre().toLowerCase().contains(query.toLowerCase()) && venue.getCategoria_id() != -1) {
                    currentList.add(venue);
                }
            }
        }
        mRecyclerView.setActivated(!rawList.isEmpty());
        notifyDataSetChanged();
    }

    public void submit(List<Venue> venues) {
        rawList.addAll(venues);
        mRecyclerView.setActivated(!rawList.isEmpty());
    }
}
