package de.skash.narutobot.core.model;

public record Ninja(
        int id,
        int health,
        int strength,
        int level,
        int maxLevel,
        String imageURL
) {
}
