package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CommandBulkCopy implements ICommand {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("bulkcopy")
                .executes(CommandUtils.execPlayerOnly(this::run))
        );
    }

    public int run(CommandContext<ServerCommandSource> ctx, PlayerEntity player) {
        ItemStack hand = player.getInventory().getSelectedStack();
        if (hand.isEmpty()) {
            ctx.getSource().sendError(Text.literal("Must be holding an item to copy."));
            return 0;
        }

        Item item = hand.getItem();
        ComponentChanges changes = getComponentChanges(hand);

        for (ItemStack stack : player.getInventory().getMainStacks()) {//find each box
            if (stack.getItem() == Items.SHULKER_BOX) {
                ContainerComponent box = stack.get(DataComponentTypes.CONTAINER);
                assert box != null;
                copyToContainer(box, item, changes);
            }
        }

        return 1;
    }

    private static ComponentChanges getComponentChanges(ItemStack source) {
        ComponentChanges.Builder builder = ComponentChanges.builder();
        builder.add(DataComponentTypes.LORE, source.get(DataComponentTypes.LORE));
        builder.add(DataComponentTypes.ITEM_NAME, source.get(DataComponentTypes.ITEM_NAME));
        builder.add(DataComponentTypes.ITEM_MODEL, source.get(DataComponentTypes.ITEM_MODEL));
        return builder.build();
    }

    private static void copyToContainer(ContainerComponent box, Item item, ComponentChanges changes) {
        for(ItemStack boxItem : box.iterateNonEmpty()){ // copy format to items in the box
            if (boxItem.getItem() == item) { //only copy if itemtype matches
                boxItem.applyChanges(changes);
            }
        }
    }

}