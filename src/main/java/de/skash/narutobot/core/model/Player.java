package de.skash.narutobot.core.model;

import java.util.List;

public record Player(long discordId, int ryo, int gems, List<Ninja> ninjas) {
}
