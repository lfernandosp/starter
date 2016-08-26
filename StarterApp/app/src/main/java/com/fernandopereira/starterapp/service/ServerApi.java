package com.fernandopereira.starterapp.service;

import com.fernandopereira.starterapp.model.FooResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerApi {

    @GET("fooItems/aaa")
    Observable<FooResponse> loadItems(@Query("param1") int param1,
                                      @Query("param2") int param2);

}
