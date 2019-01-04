package com.example.admin.githubsearch.view;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void removeView();
}
