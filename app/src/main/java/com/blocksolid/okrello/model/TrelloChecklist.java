package com.blocksolid.okrello.model;

/**
 * Created by Dan Buckland on 02/12/2015.
 */
public class TrelloChecklist {

    private String id;
    private String name;

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

}
