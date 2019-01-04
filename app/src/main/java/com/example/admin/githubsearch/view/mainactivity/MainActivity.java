package com.example.admin.githubsearch.view.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.admin.githubsearch.R;
import com.example.admin.githubsearch.model.Repository;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    MainActivityPresenter presenter;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etSearch);
        presenter = new MainActivityPresenter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        presenter.attachView(this);
        List<Repository> itemList = new ArrayList<>();
        MainActivityAdapter mainActivityAdapter = new MainActivityAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(mainActivityAdapter);

    }


    @Override
    public void onListUpdated(List<Repository> itemList) {
        MainActivityAdapter mainActivityAdapter = new MainActivityAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
    @Override
    public void showError(String e) {

    }

    public void onSearch(View view) {
        presenter.updateList(etSearch.getText().toString());
    }
}


