package de.skash.narutobot.core.networking;

import de.skash.narutobot.Bot;
import okhttp3.RequestBody;

public class RequestManager {
    private final Bot bot;

    public RequestManager(Bot bot) {
        this.bot = bot;
    }

    public <T> ApiRequest<T> createRequest(Route route, RequestBody body) {
        return new ApiRequest<>(bot, route, body);
    }
}
