package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviedb.R;
import com.example.moviedb.adapter.nowPlayingAdapter;
import com.example.moviedb.model.nowPlaying;
import com.example.moviedb.viewmodel.movieViewViewModel;

public class nowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_now_playing;
    private movieViewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        rv_now_playing = findViewById(R.id.rv_nowPlaying);
        viewModel = new ViewModelProvider(nowPlayingActivity.this).get(movieViewViewModel.class);
        viewModel.getNowPlaying();
        viewModel.getResultNowPlaying().observe(nowPlayingActivity.this, showNowPlaying);

    }

    private Observer<nowPlaying> showNowPlaying = new Observer<nowPlaying>() {
        @Override
        public void onChanged(nowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(nowPlayingActivity.this));
            nowPlayingAdapter adapter = new nowPlayingAdapter(nowPlayingActivity.this);
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };

}