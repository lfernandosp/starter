package com.fernandopereira.starterapp.inject.module;

import com.fernandopereira.starterapp.cache.PaginatedCache;
import com.fernandopereira.starterapp.cache.PaginatedMemoryCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Singleton
    @Provides
    PaginatedCache providePaginatedCache() {
        return new PaginatedMemoryCache();
    }

}
