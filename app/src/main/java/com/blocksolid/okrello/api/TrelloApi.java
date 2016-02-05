package com.blocksolid.okrello.api;

import com.blocksolid.okrello.model.TrelloCard;
import com.blocksolid.okrello.model.TrelloCheckItem;
import com.blocksolid.okrello.model.TrelloChecklist;
import com.blocksolid.okrello.model.TrelloList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Dan Buckland on 30/11/2015.
 */
public interface TrelloApi {
    String KEY = "cf2308ac2c68ab9a54037478108439e4";

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    // Get lists from board to use as time periods e.g. quarters
    @GET("boards/{boardId}/lists")
    Call<ArrayList<TrelloList>> getLists(@Path("boardId") String boardId,
                                         @Query("key") String key,
                                         @Query("fields") String fields);

    // Get cards from list to use as OKRs
    @GET("lists/{listId}/cards")
    Call<ArrayList<TrelloCard>> getCards(@Path("listId") String listId,
                                         @Query("key") String key,
                                         @Query("fields") String fields);


    // Get checklists from card to identify Key Results
    @GET("cards/{cardId}/checklists?checkItems=none")
    Call<ArrayList<TrelloChecklist>> getChecklists(@Path("cardId") String cardId,
                                                   @Query("key") String key,
                                                   @Query("fields") String fields);

    // Get checkItems from specified checklist to use as Key Results
    @GET("checklists/{checklistId}/checkItems")
    Call<ArrayList<TrelloCheckItem>> getCheckItems(@Path("checklistId") String checklistId,
                                                   @Query("key") String key,
                                                   @Query("fields") String fields);
}
