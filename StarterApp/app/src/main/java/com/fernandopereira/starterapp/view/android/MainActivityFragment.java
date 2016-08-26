package com.fernandopereira.starterapp.view.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandopereira.starterapp.R;
import com.fernandopereira.starterapp.inject.AppDependencyInjector;
import com.fernandopereira.starterapp.inject.module.ViewModule;
import com.fernandopereira.starterapp.model.Foo;
import com.fernandopereira.starterapp.view.PaginatedView;
import com.fernandopereira.starterapp.view.presenter.PaginatedViewPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements PaginatedView {

    @Inject
    PaginatedViewPresenter mPresenter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDependencyInjector.getApplicationComponent()
                .addSubmodule(new ViewModule().with(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadItems();
    }

    @Override
    public void refresh(List<Foo> freshItems) {

    }

    @Override
    public void appendNextPage(List<Foo> newItems) {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showItemDetails(String itemId) {

    }
}
