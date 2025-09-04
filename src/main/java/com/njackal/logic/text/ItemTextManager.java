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
}
