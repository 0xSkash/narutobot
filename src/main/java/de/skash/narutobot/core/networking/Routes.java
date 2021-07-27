package de.skash.narutobot.core.networking;

import de.skash.narutobot.core.model.Player;
import de.skash.narutobot.core.model.Server;
import de.skash.narutobot.core.util.ListUtils;

public class Routes {
    //Player
    public static final Route CREATE_PLAYER = new Route(HttpMethod.POST, "create_user", Player.class);

    //Server
    public static final Route UPDATE_SERVER = new Route(HttpMethod.POST, "update_server", Server.class);
    public static final Route SERVER_INDEX = new Route(HttpMethod.GET, "server", ListUtils.<Server>getTypeForList());
}
