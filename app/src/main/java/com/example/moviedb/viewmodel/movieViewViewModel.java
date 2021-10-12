package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Movies;
import com.example.moviedb.model.nowPlaying;
import com.example.moviedb.repositories.movieRepositories;

public class movieViewViewModel extends AndroidViewModel {

    private movieRepositories repositories;


    public movieViewViewModel(@NonNull Application application) {
        super(application);
        repositories = movieRepositories.getInstance();
    }

    //==begin of viewmodel get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repositories.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    //==end of viewmodel get movie


    //==Begin of viewmodel get now playing

    private MutableLiveData<nowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repositories.getNowPlayingData();
    }
    public LiveData<nowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewmodel get now playing

}
