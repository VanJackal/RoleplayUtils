package com.njackal.logic.text;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;


public class ItemTextManager implements IItemTextManager{

    @Override
    public void renameStack(ItemStack stack,String namePattern) {
        stack.set(DataComponentTypes.ITEM_NAME, Text.literal(namePattern));//todo do name processing
    }

    @Override
    public void addLoreLine(ItemStack stack, String line) {
        LoreBuilder builder = LoreBuilder.of(stack);
        builder.addLine(line);

        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void editLoreLine(ItemStack stack, String line, int lineNum) {
        LoreBuilder builder = LoreBuilder.of(stack);
        builder.setLine(lineNum, line);
        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void removeLoreLine(ItemStack stack, int lineNum) {
        LoreBuilder builder = LoreBuilder.of(stack);
        builder.removeLine(lineNum);

        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void resetLore(ItemStack stack) {
        LoreBuilder builder = LoreBuilder.of(stack);
        builder.reset();
        stack.set(DataComponentTypes.LORE, builder.build());
    }
}
