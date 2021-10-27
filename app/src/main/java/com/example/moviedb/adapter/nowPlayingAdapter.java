package com.example.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.nowPlaying;
import com.example.moviedb.view.activities.movieDetail;

import java.util.List;

public class nowPlayingAdapter extends RecyclerView.Adapter<nowPlayingAdapter.nowPlayingHolder> {

    private Context context;
    private List<nowPlaying.ResultsDTO> listNowPlaying;
    private List<nowPlaying.ResultsDTO> getListNowPlaying(){return listNowPlaying;}
    public void setListNowPlaying(List<nowPlaying.ResultsDTO> listNowPlaying){
        this.listNowPlaying = listNowPlaying;
    }
    public nowPlayingAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public nowPlayingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now, parent, false);
        return new nowPlayingAdapter.nowPlayingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull nowPlayingHolder holder, int position) {
        final nowPlaying.ResultsDTO resultsDTO = getListNowPlaying().get(position);
        holder.lbl_title.setText(resultsDTO.getTitle());
        holder.lbl_overview.setText(resultsDTO.getOverview());
        holder.lbl_realese_date.setText(resultsDTO.getRelease_date());
        Glide.with(context)
                .load(Const.IMAGE_URL+resultsDTO.getPoster_path())
                .into(holder.img_poster);

        //ini di command
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, movieDetail.class);
//                intent.putExtra("movie_id", ""+resultsDTO.getId());
//                context.startActivity(intent);

                Bundle bundle = new Bundle();
                bundle.putString("movieId", ""+resultsDTO.getId());
                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }



    public class nowPlayingHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_realese_date;
        CardView cv;

        public nowPlayingHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.image_card_now);
            lbl_title = itemView.findViewById(R.id.lbl_title_card_nowPlay);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_nowPlaying);
            lbl_realese_date = itemView.findViewById(R.id.lbl_realeaseDate_card_nowPlaying);
            cv = itemView.findViewById(R.id.cv_now);
        }
    }
}
