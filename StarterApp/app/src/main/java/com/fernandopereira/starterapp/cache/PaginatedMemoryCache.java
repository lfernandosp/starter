package com.fernandopereira.starterapp.cache;

import com.fernandopereira.starterapp.model.Foo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class PaginatedMemoryCache implements PaginatedCache {

    private final Map<String, Foo> mItemsById;
    private final List<Foo> mAllItems;
    private int mNextPage;

    public PaginatedMemoryCache() {
        mAllItems = new ArrayList<>();
        mItemsById = new HashMap<>();
        mNextPage = 1;
    }

    public synchronized void addItems(List<Foo> items) {
        mAllItems.addAll(items);
        for (Foo item : items) {
            mItemsById.put(item.getId(), item);
        }
    }

    public synchronized List<Foo> getAllItems() {
        return Collections.unmodifiableList(mAllItems);
    }

    public synchronized Foo getItem(String itemId) {
        return mItemsById.get(itemId);
    }

    public synchronized void clear() {
        mAllItems.clear();
        mItemsById.clear();
        mNextPage = 1;
    }

    public synchronized void incrementNextPage() {
        mNextPage++;
    }

    public synchronized int getNextPage() {
        return mNextPage;
    }
}
