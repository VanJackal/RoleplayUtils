package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;

public class CommandRename implements ICommand {

    private final IItemTextManager itemTextManager;

    public CommandRename(IItemTextManager itemTextManager) {
        this.itemTextManager = itemTextManager;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rename").then(
                Commands.argument("name", StringArgumentType.greedyString())
                        .executes(CommandUtils.execSelectedItem(this::run))
        ));
    }

    private int run(CommandContext<CommandSourceStack> ctx, ItemStack stack) {
        String namePattern = ctx.getArgument("name", String.class);


        itemTextManager.renameStack(stack, namePattern);

        return 1;
    }
}
