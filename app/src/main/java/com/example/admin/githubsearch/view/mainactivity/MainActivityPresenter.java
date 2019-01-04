package com.example.admin.githubsearch.view.mainactivity;

import android.util.Log;

import com.example.admin.githubsearch.data.remote.RemoteDataSource;
import com.example.admin.githubsearch.model.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityContract.Presenter {


    private static final String TAG = "MainPresenterTag";
    MainActivityContract.View view;


    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;

    }

    @Override
    public void updateList(String strSearch) {

        Call<String> gitHubDataCall = RemoteDataSource.getResponse(strSearch);

        gitHubDataCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> responseRepository) {
                Log.d(TAG, "onResponse: before");
                List<Repository> repositoryList = new ArrayList<>();
                JSONObject response = null;
                try {
                    response = new JSONObject(responseRepository.body());
                    JSONArray items = response.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = (JSONObject) items.get(i);
                        JSONObject owner = (JSONObject) item.getJSONObject("owner");
                        repositoryList.add(new Repository(
                                item.getString("name"),
                                item.getString("description"),
                                item.getInt("stargazers_count"),
                                owner.getString("avatar_url")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: " + repositoryList);
                view.onListUpdated(repositoryList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });


    }
}
