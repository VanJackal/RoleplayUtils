package com.njackal.lib.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

public interface ICommand {
    void register(CommandDispatcher<ServerCommandSource> dispatcher);
}
