package com.khanshaheb.retrorecycler.Api;

import com.khanshaheb.retrorecycler.Model.ItemModel;

import retrofit.Callback;
import retrofit.http.GET;


/**
 * Created by Joker on 7/24/16.
 */
public interface MyApi {
    @GET("/feed.json")      //here is the other url part.best way is to start using /
    public void getShout(Callback<ItemModel> response);
}
