package com.example.admin.githubsearch;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    OkHttpClient client;
    public OkHttpHelper(){
        client = new OkHttpClient();
    }


    List<Repository> run(String url){


        final Request request = new Request.Builder()
                .url(url)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Repository> repositoryList = new ArrayList<>();
                try{
                    String response = client.newCall(request).execute().body().string();
                    repositoryList = MyParser.parseItem(response);
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
        }).start();

    return repositoryList;
    }
    public void execute() {

    }
}
