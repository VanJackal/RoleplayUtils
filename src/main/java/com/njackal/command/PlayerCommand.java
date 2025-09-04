package com.njackal.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

public interface PlayerCommand {

    int run(CommandContext<ServerCommandSource> ctx, PlayerEntity player);
}
