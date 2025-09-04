package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.glow.IGlowManager;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandGlow implements ICommand {

    private final IGlowManager glowManager;

    public CommandGlow(IGlowManager glowManager) {
        this.glowManager = glowManager;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("glow")
                .executes(CommandUtils.execSelectedItem(this::enableGlow))
                .then(
                        CommandManager.argument("glowing", BoolArgumentType.bool())
                                .executes(CommandUtils.execSelectedItem(this::setGlow))
                )
        );
    }

    public int enableGlow(CommandContext<ServerCommandSource> ctx, ItemStack stack){
        glowManager.setGlow(stack, true);
        return 1;
    }

    public int setGlow(CommandContext<ServerCommandSource> ctx, ItemStack stack) {
        boolean glowing = BoolArgumentType.getBool(ctx, "glowing");
        glowManager.setGlow(stack, glowing);
        return 1;
    }


}
