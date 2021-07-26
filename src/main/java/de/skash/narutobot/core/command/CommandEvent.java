package de.skash.narutobot.core.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class CommandEvent {
    private final String[] args;
    private final Message message;
    private final User author;
    private final MessageChannel channel;

    protected CommandEvent(GuildMessageReceivedEvent event) {
        this.args = event.getMessage().getContentRaw().split("\\s");
        this.message = event.getMessage();
        this.author = event.getAuthor();
        this.channel = event.getChannel();
    }

    protected CommandEvent(PrivateMessageReceivedEvent event) {
        this.args = event.getMessage().getContentRaw().split("\\s");
        this.message = event.getMessage();
        this.author = event.getAuthor();
        this.channel = event.getChannel();
    }

    public String[] getArgs() {
        return args;
    }

    public Message getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public void reply(String text) {
        channel.sendMessage(text).queue();
    }

    public void reply(MessageEmbed embed) {
        channel.sendMessageEmbeds(embed).queue();
    }
}
