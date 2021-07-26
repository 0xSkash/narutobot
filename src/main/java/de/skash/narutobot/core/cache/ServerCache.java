package de.skash.narutobot.core.cache;

import de.skash.narutobot.core.model.Server;

public class ServerCache extends Cache<Long, Server> {

    @Override
    public void cacheElement(Server element) {
        cache.put(element.discordId(), element);
    }
}
