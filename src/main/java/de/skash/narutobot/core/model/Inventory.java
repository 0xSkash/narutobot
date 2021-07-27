package de.skash.narutobot.core.model;

import java.util.List;

public record Inventory(
        int id,
        List<Item> items
) {
}
