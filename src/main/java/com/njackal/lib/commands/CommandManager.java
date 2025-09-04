package com.njackal.lib.commands;


import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.List;

public class CommandManager {
    public void register(ICommand... commands) {
        CommandRegistrationCallback.EVENT.register((
                dispatcher,
                registry,
                environment)->{
            for (ICommand command : commands) {
                command.register(dispatcher);
            }
        });
    }
}
