package com.example.attendancemanager.di.modules.app;

import android.content.Context;
import android.util.Config;
import android.util.Log;

import com.example.attendancemanager.data.prefs.AppPreferencesHelper;
import com.example.attendancemanager.data.retrofit.APIInterface;
import com.example.attendancemanager.di.scopes.ApplicationScope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class RetrofitModule {

    private static final String TAG = "AppRetrofitModule";

    static String BASE_URL = "http://10.0.2.2:3000/";


    @Provides
    public Retrofit provideRetrofit(AppPreferencesHelper appPreferencesHelper){

        String authToken = "Bearer "+ appPreferencesHelper.getAccessToken();
        Log.d(TAG, "provideRetrofit: " + authToken);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request newRequest = original.newBuilder()
                                .header("Authorization", "Bearer " + appPreferencesHelper.getAccessToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient).build();


        return retrofit;
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                Request request = original.newBuilder()
//                        .header("Authorization" , authToken)
//                        .method(original.method(), original.body()).build();
//                Log.d(TAG, "intercept: this shit");
//                return chain.proceed(request);
//            }
//        });
//
//        if (!httpClient.interceptors().contains(logging)) {
//            httpClient.addInterceptor(logging);
//            httpClient.connectTimeout(60, TimeUnit.SECONDS);
//            httpClient.callTimeout(60, TimeUnit.SECONDS);
//
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
//
//        return retrofit;
    }

}
