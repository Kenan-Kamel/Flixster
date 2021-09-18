package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel

public class Movie {
    int movie_id ;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating ;


    // empty constructor needed by the Parceler library
    public Movie() {

    }
    public Movie (JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");

        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
       rating =  jsonObject.getDouble("vote_average") ;
       movie_id = jsonObject.getInt("id");



    }



    public static List<Movie> fromJsonArray(JSONArray moiveJsonArray) throws JSONException {
        List<Movie> movies  = new ArrayList<>();
        for(int i = 0; i < moiveJsonArray.length(); i++)
        {
            movies.add(new Movie(moiveJsonArray.getJSONObject(i)));

        }
        return movies ;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s" ,posterPath) ;

    }

    public String getTitle() {
        return title;
    }
    public String getBackdropPath()
    {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }
}
