package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandLore implements ICommand {

    private final IItemTextManager itemTextManager;
    public CommandLore(IItemTextManager itemTextManager) {
        this.itemTextManager = itemTextManager;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("lore").then(
                CommandManager.literal("add").then(
                        CommandManager.argument("text", StringArgumentType.greedyString()).executes(CommandUtils.execPlayerOnly(this::add))
                )
        ));
    }

    public int add(CommandContext<ServerCommandSource> context, PlayerEntity player) {
        String line = context.getArgument("text", String.class);
        ItemStack stack = player.getInventory().getSelectedStack();
        itemTextManager.addLoreLine(stack, line);

        return 1;
    }
}
