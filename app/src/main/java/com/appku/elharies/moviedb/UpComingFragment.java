package com.appku.elharies.moviedb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appku.elharies.moviedb.adapter.RecycleViewAdapter;
import com.appku.elharies.moviedb.model.Results;
import com.appku.elharies.moviedb.model.Upcoming;
import com.appku.elharies.moviedb.rest.ApiClient;
import com.appku.elharies.moviedb.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    private List<Results> list;
    private RecyclerView recyclerView;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rv_upcoming);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Upcoming> upcomingCall = apiInterface.getMovieUpcoming(MainActivity.API_KEY);

        upcomingCall.enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(@NonNull Call<Upcoming> call, @NonNull Response<Upcoming> response) {
                if (response.body() != null){
                    list = response.body().getResults();
                    recyclerView.setAdapter(new RecycleViewAdapter(list, getContext()));
                    Log.d("SUKSES", "Apakah sukses: "+response.isSuccessful());
                }else {
                    Log.e("GAGAL", "Gagal mengambil data "+response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Upcoming> call, @NonNull Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
        // Inflate the layout for this fragment
        return view ;
    }

}
