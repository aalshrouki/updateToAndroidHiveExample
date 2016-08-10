package com.khanshaheb.retrorecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khanshaheb.retrorecycler.Adapter.FeedAdapter;
import com.khanshaheb.retrorecycler.Api.MyApi;
import com.khanshaheb.retrorecycler.Model.ItemModel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    String API = "http://api.androidhive.info/feed/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API).addConverterFactory(GsonConverterFactory.create(gson)).build();

//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint(API).build();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        MyApi myApi = retrofit.create(MyApi.class);


        myApi.getShout(new retrofit2.Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, retrofit2.Response<ItemModel> response) {
                ItemModel itemModel = response.body();
                FeedAdapter adapter = new FeedAdapter(itemModel);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"boom !",Toast.LENGTH_LONG).show();
            }
        });

// with retrofit 1.9.0
//        myApi.getShout(new Callback<ItemModel>() {
//
//            @Override
//            public void success(ItemModel itemModel, Response response) {
//
//                FeedAdapter adapter = new FeedAdapter(itemModel);
//                mRecyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getApplicationContext(),"boom !",Toast.LENGTH_LONG).show();
//
//            }
//        });


    }
}
