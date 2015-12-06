package com.blocksolid.okrello.api;

import com.blocksolid.okrello.model.TrelloCard;
import com.blocksolid.okrello.model.TrelloList;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Dan Buckland on 30/11/2015.
 */
public interface TrelloApi {
    public static final String BASE_URL = "https://api.trello.com/1/";
    public static final String KEY = "cf2308ac2c68ab9a54037478108439e4";

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    // Get lists from board
    @GET("boards/{boardId}/lists")
    Call<List<TrelloList>> getLists(@Path("boardId") String boardId,
                                    @Query("key") String key);

    // Get cards from list
    @GET("lists/{listId}/cards")
    Call<List<TrelloCard>> getCards(@Path("listId") String listId,
                                    @Query("key") String key);
}
