package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CommandBulkCopy implements ICommand {

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("bulkcopy")
                .executes(CommandUtils.execPlayerOnly(this::run))
        );
    }

    public int run(CommandContext<CommandSourceStack> ctx, Player player) {
        ItemStack hand = player.getInventory().getSelectedItem();
        if (hand.isEmpty()) {
            ctx.getSource().sendFailure(Component.literal("Must be holding an item to copy."));
            return 0;
        }

        Item item = hand.getItem();
        DataComponentPatch changes = getComponentChanges(hand);

        for (ItemStack stack : player.getInventory()) {//find each box
            if (stack.getItem() == Items.SHULKER_BOX) {
                ItemContainerContents box = stack.get(DataComponents.CONTAINER);
                assert box != null;
                ItemContainerContents newContent = remakeBoxContents(box, item, changes);
                stack.set(DataComponents.CONTAINER, newContent);
            }
        }

        return 1;
    }

    private static DataComponentPatch getComponentChanges(ItemStack source) {
        DataComponentPatch.Builder builder = DataComponentPatch.builder();
        builder.set(DataComponents.LORE, source.get(DataComponents.LORE));
        builder.set(DataComponents.ITEM_NAME, source.get(DataComponents.ITEM_NAME));
        builder.set(DataComponents.ITEM_MODEL, source.get(DataComponents.ITEM_MODEL));
        Component name = source.get(DataComponents.CUSTOM_NAME);
        if (name != null) {
            builder.set(DataComponents.CUSTOM_NAME, name);
        }
        Boolean glow = source.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
        if (glow != null) {
            builder.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, glow);
        }
        return builder.build();
    }

    private static ItemContainerContents remakeBoxContents(ItemContainerContents box, Item item, DataComponentPatch changes) {
        List<ItemStack> items = box.allItemsCopyStream().map((s)-> {
            if (s.isEmpty()) {
                return ItemStack.EMPTY;
            } else if (s.getItem() == item) {
                s.applyComponents(changes);
                return s;
            } else {
                return s;
            }
        }).toList();
        return ItemContainerContents.fromItems(items);
    }

}