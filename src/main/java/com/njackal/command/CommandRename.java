package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRename implements ICommand {

    private final IItemTextManager itemTextManager;

    public CommandRename(IItemTextManager itemTextManager) {
        this.itemTextManager = itemTextManager;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("rename").then(
                CommandManager.argument("name", StringArgumentType.string())
                        .executes(CommandUtils.execSelectedItem(this::run))
        ));
    }

    private int run(CommandContext<ServerCommandSource> ctx, ItemStack stack) {
        String namePattern = ctx.getArgument("name", String.class);


        itemTextManager.renameStack(stack, namePattern);

        return 1;
    }
}
