package de.skash.narutobot.core.command;

import de.skash.narutobot.feature.Bot;
import de.skash.narutobot.core.cache.ServerCache;
import de.skash.narutobot.core.util.EmbedBuilderUtils;
import de.skash.narutobot.core.util.PermissionUtils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    private final CommandHandler commandHandler;
    private final ServerCache serverCache;

    public MessageListener(Bot bot) {
        this.commandHandler = bot.getCommandHandler();
        this.serverCache = bot.getServerCache();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        var message = event.getMessage();
        var args = message.getContentRaw().split("\\s");
        var guild = event.getGuild();
        var server = serverCache.getElementByKey(event.getGuild().getIdLong());
        var keyword = args[0].substring(server.prefix().length());
        var command = commandHandler.getCommandByKeywordOrNull(keyword);
        var selfMember = guild.getSelfMember();
        var author = event.getAuthor();

        if (!args[0].startsWith(server.prefix()))
            return;

        if (command == null)
            return;

        if (!selfMember.hasPermission(command.getRequiredBotPermissions())) {
            var permissionsEmbed = EmbedBuilderUtils.createErrorEmbed();
            permissionsEmbed.setTitle("The Bot is missing following permissions:");
            var stringBuilder = new StringBuilder();
            PermissionUtils.getMissingPermissions(selfMember, command.getRequiredBotPermissions()).forEach(permission -> {
                stringBuilder.append("- ").append(permission.getName()).append("\n");
            });
            permissionsEmbed.setDescription(stringBuilder.toString());
            event.getChannel().sendMessageEmbeds(permissionsEmbed.build()).queue();
            return;
        }

        guild.retrieveMember(author).queue(member -> {
            if (!member.hasPermission(command.getRequiredMemberPermission())) {
                var permissionsEmbed = EmbedBuilderUtils.createErrorEmbed();
                permissionsEmbed.setTitle("You are missing following permissions:");
                var stringBuilder = new StringBuilder();
                PermissionUtils.getMissingPermissions(member, command.getRequiredMemberPermission()).forEach(permission -> {
                    stringBuilder.append("- ").append(permission.getName()).append("\n");
                });
                permissionsEmbed.setDescription(stringBuilder.toString());
                event.getChannel().sendMessageEmbeds(permissionsEmbed.build()).queue();
            } else {
                command.onCommand(new CommandEvent(event));
            }
        });
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        //TODO
        if (event.getAuthor().isBot())
            return;
    }
}
