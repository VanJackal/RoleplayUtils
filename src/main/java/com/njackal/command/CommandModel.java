package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.model.IModelManager;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class CommandModel implements ICommand {

    private final IModelManager modelManager;

    public CommandModel(IModelManager modelManager) {
        this.modelManager = modelManager;
    }
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("model")
                .then(
                        CommandManager.literal("set").then(
                                CommandManager.argument("model", IdentifierArgumentType.identifier())
                                        .executes(CommandUtils.execSelectedItem(this::set))
                        )
                )
                .then(
                        CommandManager.literal("reset")
                                .executes(CommandUtils.execSelectedItem(this::reset))
                )
        );
    }

    public int set(CommandContext<ServerCommandSource> context, ItemStack stack) {
        Identifier model = context.getArgument("model", Identifier.class);
        modelManager.setModel(stack, model);
        return 1;
    }

    public int reset(CommandContext<ServerCommandSource> context, ItemStack stack) {
        modelManager.resetModel(stack);
        return 1;
    }
}
