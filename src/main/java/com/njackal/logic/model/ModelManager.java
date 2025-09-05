package com.njackal.logic.model;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModelManager implements IModelManager {
    @Override
    public void setModel(ItemStack stack, Identifier model) {
        stack.set(DataComponentTypes.ITEM_MODEL, model);
    }

    @Override
    public void resetModel(ItemStack stack) {
        stack.set(DataComponentTypes.ITEM_MODEL, null);
    }
}
