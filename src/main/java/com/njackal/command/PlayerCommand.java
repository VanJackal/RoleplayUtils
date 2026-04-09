package com.njackal.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.commands.CommandSourceStack;

public interface PlayerCommand {

    int run(CommandContext<CommandSourceStack> ctx, Player player);
}
