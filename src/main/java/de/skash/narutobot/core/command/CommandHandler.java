package de.skash.narutobot.core.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandHandler {
    private final Map<String, Command> commands = new ConcurrentHashMap<>();

    public void registerCommands(Command... commandsToRegister) {
        Arrays.asList(commandsToRegister).forEach(command -> {
            commands.put(command.getKeyword(), command);
        });
    }

    public Command getCommandByKeywordOrNull(String keyword) {
        return commands.get(keyword);
    }

    public void unloadCommand(Command command) {
        commands.remove(command.getKeyword());
    }
}
