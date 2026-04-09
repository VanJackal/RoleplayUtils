package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;

public class CommandLore implements ICommand {

    private final IItemTextManager itemTextManager;
    public CommandLore(IItemTextManager itemTextManager) {
        this.itemTextManager = itemTextManager;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        IntegerArgumentType lineArg = IntegerArgumentType.integer(1, ItemLore.MAX_LINES);
        dispatcher.register(Commands.literal("lore")
                .then(
                        Commands.literal("add").then(
                                Commands.argument("text", StringArgumentType.greedyString())
                                        .executes(CommandUtils.execSelectedItem(this::add))
                        )
                )
                .then(
                        Commands.literal("remove").then(
                                Commands.argument("line", lineArg)
                                        .executes(CommandUtils.execSelectedItem(this::remove))
                        )
                )
                .then(
                        Commands.literal("edit").then(
                                Commands.argument("line", lineArg).then(
                                        Commands.argument("text", StringArgumentType.greedyString())
                                            .executes(CommandUtils.execSelectedItem(this::edit))
                                )
                        )
                )
                .then(
                        Commands.literal("reset")
                                .executes(CommandUtils.execSelectedItem(this::reset))
                )
        );
    }

    public int add(CommandContext<CommandSourceStack> context, ItemStack stack) {
        String line = context.getArgument("text", String.class);
        itemTextManager.addLoreLine(stack, line);

        return 1;
    }

    public int edit(CommandContext<CommandSourceStack> context, ItemStack stack) {
        String text = context.getArgument("text", String.class);
        int lineNum = context.getArgument("line", Integer.class)-1;

        itemTextManager.editLoreLine(stack, text, lineNum);

        return 1;
    }

    public int remove(CommandContext<CommandSourceStack> context, ItemStack stack) {
        int lineNum = context.getArgument("line", Integer.class)-1;
        itemTextManager.removeLoreLine(stack,lineNum);
        return 1;
    }

    public int reset(CommandContext<CommandSourceStack> context, ItemStack stack) {
        itemTextManager.resetLore(stack);
        return 1;
    }
}
