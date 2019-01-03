package com.example.admin.githubsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Repository> repositoryList;
    OkHttpHelper okhttpHelper;
    EditText etRepository;
    MyAdapter myAdapter;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositoryList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        etRepository = findViewById(R.id.etRepository);
        okhttpHelper = new OkHttpHelper();
        mRecyclerView = findViewById(R.id.recycler_view);
        myAdapter = new MyAdapter(repositoryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myAdapter);

    }

    public void onSearch(View view) {
        repositoryList = okhttpHelper.run("https://api.github.com/search/repositories?q="
                + etRepository.getText().toString() + "&sort=stars&order=desc");


    }
}
