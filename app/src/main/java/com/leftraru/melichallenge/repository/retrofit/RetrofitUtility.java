package com.leftraru.melichallenge.repository.retrofit;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtility extends Application {

    final String TAG = getClass().getSimpleName();
    private static RetrofitUtility mInstance;
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://api.mercadolibre.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized RetrofitUtility getmInstance() {
        return mInstance;
    }

    public static Retrofit getRetrofitClient() {
        if(retrofit == null){
            okhttp3.OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.mercadolibre.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
