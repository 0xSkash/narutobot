package de.skash.narutobot.core.util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedBuilderUtils {
    public static EmbedBuilder createErrorEmbed() {
        return new EmbedBuilder().setColor(Color.RED);
    }
}
