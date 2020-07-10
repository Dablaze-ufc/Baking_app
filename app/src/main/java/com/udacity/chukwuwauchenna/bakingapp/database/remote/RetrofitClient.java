package com.udacity.chukwuwauchenna.bakingapp.database.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import androidx.viewbinding.BuildConfig;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.BASE_URL;

/**
 * Created by ChukwuwaUchenna
 */
public class RetrofitClient {
    private static Retrofit sRetrofit = null;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return sRetrofit;
    }
}
