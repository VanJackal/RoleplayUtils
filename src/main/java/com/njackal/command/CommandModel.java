package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.model.IModelManager;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.Identifier;

public class CommandModel implements ICommand {

    private final IModelManager modelManager;

    public CommandModel(IModelManager modelManager) {
        this.modelManager = modelManager;
    }
    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("model")
                .then(
                        Commands.literal("set").then(
                                Commands.argument("model", IdentifierArgument.id())
                                        .executes(CommandUtils.execSelectedItem(this::set))
                        )
                )
                .then(
                        Commands.literal("reset")
                                .executes(CommandUtils.execSelectedItem(this::reset))
                )
        );
    }

    public int set(CommandContext<CommandSourceStack> context, ItemStack stack) {
        Identifier model = context.getArgument("model", Identifier.class);
        modelManager.setModel(stack, model);
        return 1;
    }

    public int reset(CommandContext<CommandSourceStack> context, ItemStack stack) {
        modelManager.resetModel(stack);
        return 1;
    }
}
