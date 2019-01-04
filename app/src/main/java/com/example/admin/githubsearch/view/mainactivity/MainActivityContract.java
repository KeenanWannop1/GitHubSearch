package com.example.admin.githubsearch.view.mainactivity;

import com.example.admin.githubsearch.model.Repository;
import com.example.admin.githubsearch.view.BasePresenter;
import com.example.admin.githubsearch.view.BaseView;

import java.util.List;

public interface MainActivityContract {


    interface View extends BaseView {


        void onListUpdated(List<Repository> items);

    }


    interface Presenter extends BasePresenter<View> {

        void updateList(String strSearch);



    }
}
