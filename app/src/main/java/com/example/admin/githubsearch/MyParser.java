package com.example.admin.githubsearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyParser {



    public static List<Repository> parseItem(final String responseStr){
        List<Repository> repositoryList = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(responseStr);
            JSONArray items = response.getJSONArray("items");
            for(int i = 0; i < items.length(); i++){
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
        return repositoryList;
    }

}
