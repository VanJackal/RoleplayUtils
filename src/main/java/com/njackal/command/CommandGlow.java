package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import com.njackal.logic.glow.IGlowManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;

public class CommandGlow implements ICommand {

    private final IGlowManager glowManager;

    public CommandGlow(IGlowManager glowManager) {
        this.glowManager = glowManager;
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("glow")
                .executes(CommandUtils.execSelectedItem(this::enableGlow))
                .then(
                        Commands.argument("glowing", BoolArgumentType.bool())
                                .executes(CommandUtils.execSelectedItem(this::setGlow))
                )
        );
    }

    public int enableGlow(CommandContext<CommandSourceStack> ctx, ItemStack stack){
        glowManager.setGlow(stack, true);
        return 1;
    }

    public int setGlow(CommandContext<CommandSourceStack> ctx, ItemStack stack) {
        boolean glowing = BoolArgumentType.getBool(ctx, "glowing");
        glowManager.setGlow(stack, glowing);
        return 1;
    }


}
