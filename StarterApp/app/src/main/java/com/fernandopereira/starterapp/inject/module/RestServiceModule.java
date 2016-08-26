package com.fernandopereira.starterapp.inject.module;

import com.fernandopereira.starterapp.service.ServerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestServiceModule {

    private static final String BASE_URL = "https://foo.com/bar/";

    @Singleton
    @Provides
    ServerApi provideServerApi(Retrofit retrofit) {
        return retrofit.create(ServerApi.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(HttpUrl.parse(BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Interceptor globalParamsInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(globalParamsInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Interceptor provideGlobalParamsInterceptor() {
        return (chain -> {
            Request request = chain.request();

            HttpUrl modifiedUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("name", "value")
                    .build();

            Request modifiedRequest = request.newBuilder()
                    .url(modifiedUrl)
                    .build();

            return chain.proceed(modifiedRequest);
        });
    }
}