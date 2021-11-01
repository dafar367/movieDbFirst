package com.example.moviedb.view.fragments;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.view.activities.movieDetail;
import com.example.moviedb.viewmodel.movieViewViewModel;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link movieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class movieDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public movieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static movieDetailFragment newInstance(String param1, String param2) {
        movieDetailFragment fragment = new movieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_movie_title,lbl_movie_overview,lbl_movie_rating,lbl_tagline,lbl_realeseDate,lbl_genres;
    private String movie_id = "";
    private movieViewViewModel viewModel;
    private ImageView img_poster,image_backdrop;
    private LinearLayout ll_production;
    private String movGen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(movieViewViewModel.class);
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);


        movie_id = getArguments().getString("movieId");

        img_poster = view.findViewById(R.id.img_poster_movieDetail);
        image_backdrop = view.findViewById(R.id.img_backdrop_detail);
        lbl_movie_title = view.findViewById(R.id.lbl_title_detail);
        lbl_movie_overview = view.findViewById(R.id.lbl_overview_detail);
        lbl_movie_rating = view.findViewById(R.id.lbl_rating_detail);
        ll_production = view.findViewById(R.id.ll_movieProduction_detail);
        lbl_tagline = view.findViewById(R.id.lbl_tagline_detail);
        lbl_realeseDate = view.findViewById(R.id.lbl_realeseDate_detail);
        lbl_genres = view.findViewById(R.id.lbl_genre_detail);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);

//        String movieId = getArguments().getString("movieId");
//        Movies movies = getArguments().getParcelable("sccs")
        return view;
    }

    private final Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_backdrop = Const.IMAGE_URL + movies.getBackdrop_path();
            String img_path = Const.IMAGE_URL + movies.getPoster_path().toString();
            Glide.with(movieDetailFragment.this).load(img_backdrop).into(image_backdrop);
            Glide.with(movieDetailFragment.this).load(img_path).into(img_poster);
            lbl_movie_title.setText(movies.getTitle());
            lbl_movie_overview.setText(movies.getOverview());
            lbl_movie_rating.setText(""+movies.getVote_average());
            lbl_tagline.setText(movies.getTagline());
            lbl_realeseDate.setText(movies.getRelease_date());

            for (int i = 1; i<movies.getGenres().size();i++){
                if (i == movies.getGenres().size()){
                    movGen=movies.getGenres().get(i).getName();
                }else {
                    movGen=movies.getGenres().get(i).getName()+", ";
                }
            }

            for(int i = 0; i < movies.getProduction_companies().size();i++){
                ImageView img_prod = new ImageView(ll_production.getContext());
                String productionLogo = Const.IMAGE_URL + movies.getProduction_companies().get(i).getLogo_path();
                String productionName = movies.getProduction_companies().get(i).getName();
                if(movies.getProduction_companies().get(i).getLogo_path()==null){
                    img_prod.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_movie_24, getActivity().getTheme()));
                }else if(productionLogo != "https://image.tmdb.org/3/t/p/w500/null"){
                    Glide.with(getActivity()).load(productionLogo).into(img_prod);
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(250,250);
                lp.setMargins(25,0,25,0);
                img_prod.setLayoutParams(lp);
                ll_production.addView(img_prod);
                img_prod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar = Snackbar.make(view, productionName, Snackbar.LENGTH_SHORT);
                        snackbar.setAnchorView(R.id.buttom_navs_menu);
                        snackbar.show();
                    }
                });
            }

            lbl_genres.setText(movGen);
        }
    };


}