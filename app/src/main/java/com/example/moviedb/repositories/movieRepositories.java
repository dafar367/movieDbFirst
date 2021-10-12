package com.example.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.nowPlaying;
import com.example.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movieRepositories {
    
    private static movieRepositories repositories;

    private movieRepositories(){}

    public static movieRepositories getInstance(){
        if (repositories == null){
            repositories = new movieRepositories();
        }
        return repositories;
    }

    public MutableLiveData<Movies> getMovieData(String movieId){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMoviesById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
        return result;
    }

    public MutableLiveData<nowPlaying> getNowPlayingData(){
        final MutableLiveData<nowPlaying> result = new MutableLiveData<>();
        ApiService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<nowPlaying>() {
            @Override
            public void onResponse(Call<nowPlaying> call, Response<nowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<nowPlaying> call, Throwable t) {

            }
        });

        return result;
    }

}
