package de.skash.narutobot.core.repository;

import de.skash.narutobot.core.cache.ServerCache;
import de.skash.narutobot.core.model.Server;
import de.skash.narutobot.core.networking.ApiRequest;
import de.skash.narutobot.core.networking.Route;
import de.skash.narutobot.core.networking.Routes;
import de.skash.narutobot.feature.Bot;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.List;

public class ApiServerRepository implements ServerRepository {
    private final ServerCache serverCache;
    private final Bot bot;

    public ApiServerRepository(Bot bot) {
        this.serverCache = bot.getServerCache();
        this.bot = bot;
    }

    @Override
    public Server getServerById(long id) {
        return serverCache.getElementByKey(id);
    }

    @Override
    public ApiRequest<Server> updateServer(Server server) {
        return new ApiRequest<>(bot, Routes.UPDATE_SERVER, RequestBody.create(bot.getGson().toJson(server), Bot.JSON));
    }

    @Override
    public ApiRequest<List<Server>> loadAllServer() {
        return null;
    }

    @Override
    public ApiRequest<Void> deleteServer(long id) {
        return null;
    }
}
