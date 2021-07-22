package de.skash.narutobot;

import com.google.gson.Gson;
import de.skash.narutobot.core.networking.RequestManager;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class Bot {
    private final OkHttpClient httpClient;
    private final Gson gson;
    private RequestManager requestManager;

    private Bot() {
        httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        gson = new Gson();
    }

    public static void main(String[] args) {

    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Gson getGson() {
        return gson;
    }

    public RequestManager getRequestManager() {
        if (requestManager == null)
            requestManager = new RequestManager(this);
        return requestManager;
    }
}
