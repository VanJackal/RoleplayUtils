package com.njackal.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;

public interface SelectedItemCommand {
    int run(CommandContext<ServerCommandSource> ctx, ItemStack stack);
}
