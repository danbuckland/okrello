package com.blocksolid.okrello.api;

import com.blocksolid.okrello.model.TrelloList;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Dan Buckland on 30/11/2015.
 */
public interface TrelloApi {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("boards/5RMq1Nyb/lists")
    Call<List<TrelloList>> getLists(@Query("key") String key);
}
