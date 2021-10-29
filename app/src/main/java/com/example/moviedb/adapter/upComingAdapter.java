package com.example.moviedb.adapter;

import android.content.Context;
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
import com.example.moviedb.model.upComing;

import java.util.List;

public class upComingAdapter extends RecyclerView.Adapter<upComingAdapter.upComingHolder> {

    private Context context;
    private List<upComing.Results> listUpcoming;
    private List<upComing.Results> getListUpcoming(){return listUpcoming;}
    public void setListUpcoming(List<upComing.Results> listUpcoming){
        this.listUpcoming = listUpcoming;
    }
    //dipanggil di class upComing fragment
    public upComingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public upComingAdapter.upComingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_upcoming, parent, false);
        return new upComingAdapter.upComingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull upComingAdapter.upComingHolder holder, int position) {
        final upComing.Results results = getListUpcoming().get(position);
        holder.lbl_title_upComing.setText(results.getTitle());
        holder.lbl_overview_upComing.setText(results.getOverview());
        holder.lbl_realese_date_upComing.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMAGE_URL+results.getPoster_path())
                .into(holder.img_poster_upComing);
        holder.cv_upComing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movieId", ""+results.getId());
                Navigation.findNavController(view).navigate(R.id.action_upComingFragment_to_movieDetailFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListUpcoming().size();
    }

    public class upComingHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_upComing;
        TextView lbl_title_upComing, lbl_overview_upComing, lbl_realese_date_upComing;
        CardView cv_upComing;
        public upComingHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_upComing = itemView.findViewById(R.id.image_card_upcoming);
            lbl_title_upComing = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview_upComing = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_realese_date_upComing = itemView.findViewById(R.id.lbl_realeaseDate_card_upcoming);
            cv_upComing = itemView.findViewById(R.id.cv_upcoming);
        }
    }
}
