package com.njackal.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.njackal.lib.commands.ICommand;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

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
        List<ComponentCopyHelper<?>> helpers = getComponentTypes(hand);
        System.out.println(item);

        for (ItemStack stack : player.getInventory().getMainStacks()) {//find each box
            if (stack.getItem() == Items.SHULKER_BOX) {
                ContainerComponent box = stack.get(DataComponentTypes.CONTAINER);
                assert box != null;
                copyToContainer(box, item, helpers);
            }
        }

        return 1;
    }

    private static void copyToContainer(ContainerComponent box, Item item, List<ComponentCopyHelper<?>> helpers) {
        for(ItemStack boxItem : box.iterateNonEmpty()){ // copy format to items in the box
            if (boxItem.getItem() == item) { //only copy if itemtype matches
                for (ComponentCopyHelper<?> helper : helpers) {
                    helper.apply(boxItem);
                }
            }
        }
    }


    private List<ComponentCopyHelper<?>> getComponentTypes(ItemStack stack) {
        List<ComponentCopyHelper<?>> componentTypes = new ArrayList<>();

        componentTypes.add(ComponentCopyHelper.of(DataComponentTypes.LORE, stack.get(DataComponentTypes.LORE)));
        componentTypes.add(ComponentCopyHelper.of(DataComponentTypes.ITEM_NAME, stack.get(DataComponentTypes.ITEM_NAME)));
        componentTypes.add(ComponentCopyHelper.of(DataComponentTypes.ITEM_MODEL, stack.get(DataComponentTypes.ITEM_MODEL)));
        return componentTypes;
    }


}

class ComponentCopyHelper<T>{
    private final ComponentType<T> componentType;
    private final T value;

    public ComponentCopyHelper(ComponentType<T> componentType, T value){
        this.componentType = componentType;
        this.value = value;
    }

    public static <G> ComponentCopyHelper<G> of(ComponentType<G> componentType, G value){
        return new ComponentCopyHelper<>(componentType, value);
    }

    public void apply(ItemStack stack) {
        stack.set(componentType, value);
    }
}