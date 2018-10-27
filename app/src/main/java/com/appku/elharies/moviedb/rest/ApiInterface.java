package com.appku.elharies.moviedb.rest;

import com.appku.elharies.moviedb.model.Latest;
import com.appku.elharies.moviedb.model.NowPlaying;
import com.appku.elharies.moviedb.model.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by El Haries on 9/3/2018.
 */

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<NowPlaying> getMovieNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/latest")
    Call<Latest> getMovieLatest(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<Upcoming> getMovieUpcoming(@Query("api_key") String apiKey);
}
