package com.fernandopereira.starterapp.inject.module;

import com.fernandopereira.starterapp.cache.PaginatedCache;
import com.fernandopereira.starterapp.service.ServerApi;
import com.fernandopereira.starterapp.view.PaginatedView;
import com.fernandopereira.starterapp.view.presenter.PaginatedViewPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PaginatedViewModule {

    private PaginatedView mPaginatedView;

    public PaginatedViewModule(PaginatedView paginatedView) {
        mPaginatedView = paginatedView;
    }

    @Provides
    public PaginatedViewPresenter providePaginatedViewPresenter(ServerApi serverApi,
                                                                PaginatedCache paginatedCache) {
        return new PaginatedViewPresenter(mPaginatedView, serverApi, paginatedCache);
    }
}
