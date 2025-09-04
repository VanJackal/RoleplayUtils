package com.njackal.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CommandUtils {
    static public Command<ServerCommandSource> execPlayerOnly(PlayerCommand command) {
        return (CommandContext<ServerCommandSource> ctx) -> {
            PlayerEntity player = ctx.getSource().getPlayer();
            if (player == null) {
                ctx.getSource().sendError(Text.of("Command must be executed by a player"));
            }
            return command.run(ctx, player);
        };
    }

    static public Command<ServerCommandSource> execSelectedItem(SelectedItemCommand command) {
        return execPlayerOnly(((ctx, player) -> {
            ItemStack stack = player.getInventory().getSelectedStack();
            return command.run(ctx, stack);
        }));

    }
}
