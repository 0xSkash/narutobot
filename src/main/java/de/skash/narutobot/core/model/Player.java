package de.skash.narutobot.core.model;

import java.util.List;
import java.util.Set;

public record Player(long discordId, int ryo, int gems, List<Ninja> ninjas, int level, Set<Long> friendIds, Team team) {
}
