package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandLore implements ICommand {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("command").then(
                CommandManager.literal("add").then(
                        CommandManager.argument("text", StringArgumentType.greedyString()).executes(this::add)
                )
        ));
    }

    public int add(CommandContext<ServerCommandSource> context) {


        return 1;
    }
}
