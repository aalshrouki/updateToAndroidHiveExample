package com.khanshaheb.retrorecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.khanshaheb.retrorecycler.Adapter.FeedAdapter;
import com.khanshaheb.retrorecycler.Api.MyApi;
import com.khanshaheb.retrorecycler.Model.ItemModel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    String API = "http://api.androidhive.info/feed/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        MyApi myApi = restAdapter.create(MyApi.class);

        myApi.getShout(new Callback<ItemModel>() {

            @Override
            public void success(ItemModel itemModel, Response response) {

                FeedAdapter adapter = new FeedAdapter(itemModel);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),"boom !",Toast.LENGTH_LONG).show();

            }
        });


    }
}
