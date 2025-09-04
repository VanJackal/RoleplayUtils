package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.component.type.LoreComponent;
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
        IntegerArgumentType lineArg = IntegerArgumentType.integer(1, LoreComponent.MAX_LORES);
        dispatcher.register(CommandManager.literal("lore")
                .then(
                        CommandManager.literal("add").then(
                                CommandManager.argument("text", StringArgumentType.greedyString())
                                        .executes(CommandUtils.execPlayerOnly(this::add))
                        )
                )
                .then(
                        CommandManager.literal("remove").then(
                                CommandManager.argument("line", lineArg)
                                        .executes(CommandUtils.execPlayerOnly(this::remove))
                        )
                )
                .then(
                        CommandManager.literal("edit").then(
                                CommandManager.argument("line", lineArg).then(
                                        CommandManager.argument("text", StringArgumentType.greedyString())
                                            .executes(CommandUtils.execPlayerOnly(this::edit))
                                )
                        )
                )
                .then(
                        CommandManager.literal("reset")
                                .executes(CommandUtils.execPlayerOnly(this::reset))
                )
        );
    }

    public int add(CommandContext<ServerCommandSource> context, PlayerEntity player) {
        String line = context.getArgument("text", String.class);
        ItemStack stack = player.getInventory().getSelectedStack();
        itemTextManager.addLoreLine(stack, line);

        return 1;
    }

    public int edit(CommandContext<ServerCommandSource> context, PlayerEntity player) {
        String text = context.getArgument("text", String.class);
        int lineNum = context.getArgument("line", Integer.class)-1;
        ItemStack stack = player.getInventory().getSelectedStack();

        itemTextManager.editLoreLine(stack, text, lineNum);

        return 1;
    }

    public int remove(CommandContext<ServerCommandSource> context, PlayerEntity player) {
        int lineNum = context.getArgument("line", Integer.class)-1;
        ItemStack stack = player.getInventory().getSelectedStack();
        itemTextManager.removeLoreLine(stack,lineNum);
        return 1;
    }

    public int reset(CommandContext<ServerCommandSource> context, PlayerEntity player) {
        ItemStack stack = player.getInventory().getSelectedStack();
        itemTextManager.resetLore(stack);
        return 1;
    }
}
