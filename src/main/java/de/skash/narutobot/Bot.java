package de.skash.narutobot;

import com.google.gson.Gson;
import de.skash.narutobot.core.RequestManager;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Bot {
    private final OkHttpClient httpClient;
    private final Gson gson;
    private final RequestManager requestManager;

    private Bot() {
        httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        gson = new Gson();
        requestManager = new RequestManager(this);
    }

    public static void main(String[] args) {

    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Gson getGson() {
        return gson;
    }
}
