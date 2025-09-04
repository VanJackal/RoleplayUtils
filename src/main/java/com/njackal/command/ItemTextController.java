package com.njackal.command;

import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.Command;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ItemTextController {

    @Command("rename")
    public int command_rename(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();
        if (source.getEntity() == null || !source.getEntity().isPlayer()){
            source.sendError(Text.literal("Command can only be executed on a player."));
            return 0;
        }// exit early if command isn't executed by a player

        PlayerEntity player = source.getPlayer();

        assert player != null;
        ItemStack selectedStack = player.getInventory().getSelectedStack();


        return 1;
    }
}
