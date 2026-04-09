package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.text.IItemTextManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;

public class CommandRenameCustom implements ICommand {
    private final IItemTextManager itemTextManager;

    public CommandRenameCustom(IItemTextManager itemTextManager){
        this.itemTextManager = itemTextManager;
    }
    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("renamecustom").then(
                Commands.argument("name", StringArgumentType.greedyString())
                        .executes(CommandUtils.execSelectedItem(this::run))
        ));
    }

    private int run(CommandContext<CommandSourceStack> ctx, ItemStack stack) {
        String name = StringArgumentType.getString(ctx, "name");
        itemTextManager.renameCustomStack(stack, name);
        return 1;
    }
}
