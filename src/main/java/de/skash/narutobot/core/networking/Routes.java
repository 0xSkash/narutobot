package de.skash.narutobot.core.networking;

import de.skash.narutobot.core.model.Player;

public class Routes {
    public static final Route ROUTE_CREATE_PLAYER = new Route(HttpMethod.POST, "create_user", Player.class);
}
