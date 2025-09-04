package com.njackal.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CommandUtils {
    static public int execPlayerOnly(CommandContext<ServerCommandSource> ctx, Command<ServerCommandSource> command) throws CommandSyntaxException {
        PlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.of("Command must be executed by a player"));
        }
        return command.run(ctx);
    }
}
