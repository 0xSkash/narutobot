package de.skash.narutobot.core.command;

import net.dv8tion.jda.api.Permission;

import java.util.Set;

public abstract class Command {
    public abstract void onCommand(CommandEvent event);

    public abstract CommandCategory getCommandCategory();

    public abstract String getKeyword();

    public abstract Set<Permission> getRequiredMemberPermission();

    public abstract Set<Permission> getRequiredBotPermissions();
}
