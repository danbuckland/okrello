package com.blocksolid.okrello.model;

import java.util.ArrayList;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class TrelloChecklist {

    private String id;
    private String name;
    private ArrayList<TrelloCheckItem> checkItems;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // TODO remove this after unit tests have been updated to use a mock backend
        this.name = name;
    }

    public ArrayList<TrelloCheckItem> getTrelloCheckItems() {
        return checkItems;
    }
}
