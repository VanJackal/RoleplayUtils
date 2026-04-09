package com.njackal.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.CommandSourceStack;

public interface SelectedItemCommand {
    int run(CommandContext<CommandSourceStack> ctx, ItemStack stack);
}
