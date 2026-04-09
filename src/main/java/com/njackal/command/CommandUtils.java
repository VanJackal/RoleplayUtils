package com.njackal.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class CommandUtils {
    static public Command<CommandSourceStack> execPlayerOnly(PlayerCommand command) {
        return (CommandContext<CommandSourceStack> ctx) -> {
            Player player = ctx.getSource().getPlayer();
            if (player == null) {
                ctx.getSource().sendError(Component.of("Command must be executed by a player"));
            }
            return command.run(ctx, player);
        };
    }

    static public Command<CommandSourceStack> execSelectedItem(SelectedItemCommand command) {
        return execPlayerOnly(((ctx, player) -> {
            ItemStack stack = player.getInventory().getSelectedStack();
            return command.run(ctx, stack);
        }));

    }
}
