package de.skash.narutobot.feature.commands;

import de.skash.narutobot.core.command.Command;
import de.skash.narutobot.core.command.CommandCategory;
import de.skash.narutobot.core.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

import java.util.Set;

public class TestCommand extends Command {
    @Override
    public void onCommand(CommandEvent event) {
        event.reply("OK!");
    }

    @Override
    public CommandCategory getCommandCategory() {
        return CommandCategory.GUILD;
    }

    @Override
    public String getKeyword() {
        return "test";
    }

    @Override
    public Set<Permission> getRequiredMemberPermission() {
        return Set.of(Permission.ADMINISTRATOR);
    }

    @Override
    public Set<Permission> getRequiredBotPermissions() {
        return Set.of(Permission.MANAGE_PERMISSIONS, Permission.NICKNAME_MANAGE, Permission.MESSAGE_MENTION_EVERYONE);
    }
}
