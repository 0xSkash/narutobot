package de.skash.narutobot.core.networking;

import com.google.gson.JsonSyntaxException;
import de.skash.narutobot.feature.Bot;
import de.skash.narutobot.core.exception.ApiException;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class ApiRequest<T> {
    public static final String BASE_URL = "127.0.0.1:8089/api";
    public static final Logger LOG = LoggerFactory.getLogger(ApiRequest.class);

    public static final RequestBody EMPTY_BODY = RequestBody.create(new byte[0]);

    private final Bot bot;
    private final Route route;
    private RequestBody body;

    protected ApiRequest(Bot bot, Route route, RequestBody body) {
        this.route = route;
        this.body = body;
        this.bot = bot;
    }

    protected ApiRequest(Bot bot, Route route) {
        this(bot, route, null);
    }

    public void execute(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        bot.getHttpClient().newCall(createRequest())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        failure.accept(e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            failure.accept(new ApiException("Failed to handle request"));
                            return;
                        }

                        if (route.getResponseType() == Void.TYPE) {
                            success.accept(null);
                            return;
                        }

                        try {
                            success.accept(bot.getGson().fromJson(response.body().string(), route.getResponseType()));
                        } catch (JsonSyntaxException e) {
                            failure.accept(new ApiException("Failed to handle request"));
                        }
                    }
                });
    }

    private Request createRequest() {
        var builder = new Request.Builder();
        builder.url(BASE_URL + route.getRoute());

        var method = route.getMethod().toString();

        if (body == null && HttpMethod.requiresRequestBody(method))
            body = EMPTY_BODY;

        builder.method(method, body);

        //Add Auth
        return builder.build();
    }


}
