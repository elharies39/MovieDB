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
import com.appku.elharies.moviedb.model.NowPlaying;
import com.appku.elharies.moviedb.model.Results;
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
public class NowPlayingFragment extends Fragment {

    private List<Results> list;
    private RecyclerView recyclerView;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rv_now_playing);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NowPlaying> call = apiService.getMovieNowPlaying(MainActivity.API_KEY);

        call.enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(@NonNull Call<NowPlaying> call, @NonNull Response<NowPlaying> response) {
                if (response.body() != null){
                    Log.d("SUKSES","Apakah sukses: "+response.isSuccessful());
                    list = response.body().getResults();
                    Log.d("NOW PLAYING","Berapa banyak data: "+list.size());
                    recyclerView.setAdapter(new RecycleViewAdapter(list, getContext()));
                }else{
                    Log.e("GAGAL","GAGAL mengambil data "+response.isSuccessful());
                }

            }

            @Override
            public void onFailure(@NonNull Call<NowPlaying> call, @NonNull Throwable t) {
                Log.e("ERRORDATA", t.toString());
            }
        });

//        recyclerView.setAdapter(new RecycleViewAdapter(list, getContext()));
        // Inflate the layout for this fragment
        return view;
    }

}
