package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.adapter.upComingAdapter;
import com.example.moviedb.model.upComing;
import com.example.moviedb.viewmodel.movieViewViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link upComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class upComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public upComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment upComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static upComingFragment newInstance(String param1, String param2) {
        upComingFragment fragment = new upComingFragment();
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

    private RecyclerView rv_upcoming_fragment;
    private movieViewViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        rv_upcoming_fragment = view.findViewById(R.id.rv_upComing_fragment);
        viewModel = new ViewModelProvider(getActivity()).get(movieViewViewModel.class);
        viewModel.getUpComing();
        viewModel.getResultUpComing().observe(getActivity(), showUpComing);
        return view;
    }

    private Observer<upComing> showUpComing = new Observer<upComing>() {
        @Override
        public void onChanged(upComing upComing) {
            rv_upcoming_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            upComingAdapter adapter = new upComingAdapter(getActivity());
            adapter.setListUpcoming(upComing.getResults());
            rv_upcoming_fragment.setAdapter(adapter);
        }
    };
}