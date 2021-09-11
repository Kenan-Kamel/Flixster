package com.codepath.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.MovieAdapter;
import models.Movie;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed" ;
    public static final String TAG = "MainActivity";
    List<Movie> movies ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMoives = findViewById(R.id.rvMovies) ;

        movies = new ArrayList<>();



        //Create the adapter
       MovieAdapter movieAdapter =  new MovieAdapter(this,movies);


        //set the adapter on the recycler view
        rvMoives.setAdapter(movieAdapter);

        // Set a Layout Manager on the recycler view
        rvMoives.setLayoutManager(new LinearLayoutManager(this));



        // Creating a new object of AsynchttpClinet class to handle
        // the the movie database request of json data

        AsyncHttpClient client = new AsyncHttpClient() ;
        // getting the results of https request and reasoned with json handler
        // since the data is in Json format

        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            // there are to outcome of the respond
            // if success then handle the results and store it in an array results
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject ;
                try {
                    JSONArray results =  jsonObject.getJSONArray("results");
                    // for Debugging
                    Log.i(TAG,"Results:" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    // for debugging
                    Log.i(TAG,"Movies: " + movies.size());


                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception") ;
                    e.printStackTrace();
                }


            }

            @Override
            // if failed print out a log about the issue
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");

            }
        });

    }
}