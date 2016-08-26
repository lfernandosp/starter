package com.fernandopereira.starterapp.view.presenter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.fernandopereira.starterapp.cache.PaginatedCache;
import com.fernandopereira.starterapp.model.Foo;
import com.fernandopereira.starterapp.service.ServerApi;
import com.fernandopereira.starterapp.view.PaginatedView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaginatedViewPresenter {

    protected final PaginatedView mMainView;
    protected final ServerApi mServerApi;
    protected final PaginatedCache mCache;

    private Subscription mLoadItemsRequestSubscription;
    private Subscription mLoadNextPageRequestSubscription;

    @Inject
    public PaginatedViewPresenter(PaginatedView paginatedView,
                                  ServerApi serverApi,
                                  PaginatedCache cache) {
        mMainView = paginatedView;
        mServerApi = serverApi;
        mCache = cache;
    }

    public void forceRefreshItems() {
        unsubscribe(mLoadItemsRequestSubscription);
        mLoadItemsRequestSubscription = loadFromNetwork()
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> unsubscribe(mLoadNextPageRequestSubscription))
                .doOnTerminate(mMainView::hideLoadingIndicator)
                .subscribe(
                        mMainView::refresh, // onNext
                        mMainView::handleError); // onError

    }

    public void loadItems() {
        unsubscribe(mLoadItemsRequestSubscription);
        mLoadItemsRequestSubscription =
                Observable.concat(
                        Observable.just(mCache.getAllItems()),
                        loadFromNetwork())
                        .first(entitiesFromCache ->
                                (entitiesFromCache != null && !entitiesFromCache.isEmpty()))

                        .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(() -> unsubscribe(mLoadNextPageRequestSubscription))
                        .doOnTerminate(mMainView::hideLoadingIndicator)

                        .subscribe(
                                mMainView::refresh, // onNext
                                mMainView::handleError); // onError
    }

    public void resetSubscriptions() {
        unsubscribe(mLoadItemsRequestSubscription);
        unsubscribe(mLoadNextPageRequestSubscription);
    }

    public void loadNextPage() {
        if (mLoadNextPageRequestSubscription != null && !mLoadNextPageRequestSubscription.isUnsubscribed()) {
            return;
        }

        mLoadNextPageRequestSubscription =
                loadFromNetwork(mCache.getNextPage())
                        .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mMainView::appendNextPage, // onNext
                                mMainView::handleError); // onError
    }

    public Foo getItem(String id) {
        return mCache.getItem(id);
    }

    @NonNull
    private Observable<List<Foo>> loadFromNetwork() {
        return loadFromNetwork(1);
    }

    @NonNull
    private Observable<List<Foo>> loadFromNetwork(int page) {
        return mServerApi.loadItems(page, 20)
                .map(fooResponse -> {
                    return null;
                });
    }

    private void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
