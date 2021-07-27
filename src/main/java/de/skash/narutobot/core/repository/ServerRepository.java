package de.skash.narutobot.core.repository;

import de.skash.narutobot.core.model.Server;
import de.skash.narutobot.core.networking.ApiRequest;

import java.util.List;

public interface ServerRepository {
    Server getServerById(long id);

    ApiRequest<Server> updateServer(Server server);

    ApiRequest<List<Server>> loadAllServer();

    ApiRequest<Void> deleteServer(long id);
}
