package com.fernandopereira.starterapp.cache;

import com.fernandopereira.starterapp.model.Foo;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public interface PaginatedCache {

    void addItems(List<Foo> items);

    List<Foo> getAllItems();

    Foo getItem(String itemId);

    void clear();

    void incrementNextPage();

    int getNextPage();
}
