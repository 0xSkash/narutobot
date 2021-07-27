package de.skash.narutobot.core.util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

public class EmbedBuilderUtils {
    public static EmbedBuilder createErrorEmbed() {
        return new EmbedBuilder().setColor(Color.RED);
    }

    public static EmbedBuilder createBaseEmbed() {
        return new EmbedBuilder()
                .setColor(Color.CYAN)
                .setTimestamp(LocalDate.now());
    }
}
