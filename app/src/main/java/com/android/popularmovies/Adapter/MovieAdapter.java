package com.android.popularmovies.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.popularmovies.Activity.DetailsActivity;
import com.android.popularmovies.Model.MovieResource;
import com.android.popularmovies.Presenter.PresenterCall;
import com.android.popularmovies.R;
import com.android.popularmovies.Utils.MovieConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kamalshree on 6/11/2018.
 */

public class MovieAdapter extends ArrayAdapter<MovieResource> {


    List<MovieResource> movieResources;
    //activity context
    private final Context context;

    ImageView imageView;


    //the layout resource file for the list items
    int resource;
    private PresenterCall mPresenter;


    //constructor initializing the values
    public MovieAdapter(Context context, int resource, List<MovieResource> movieResources, PresenterCall buttonListener) {
        super(context, resource, movieResources);
        this.context = context;
        this.resource = resource;
        this.movieResources = movieResources;
        this.mPresenter = buttonListener;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);
        //getting the movie of the specified position
        final MovieResource movie = movieResources.get(position);

        //getting the view elements of the list from the view
        imageView = (ImageView) view.findViewById(R.id.tv_imagepath);
        Picasso.get()
                .load((MovieConstants.CARD_IMAGE_VIEW + movie.getPoster_Url()).trim())
                .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.DetailsScreen(movie.getMovieId(), movie.getOriginalTitle());
            }
        });

        //finally returning the view
        return view;
    }
}