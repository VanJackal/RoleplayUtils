package com.njackal.logic.model;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;

public class ModelManager implements IModelManager {
    @Override
    public void setModel(ItemStack stack, Identifier model) {
        stack.set(DataComponents.ITEM_MODEL, model);
    }

    @Override
    public void resetModel(ItemStack stack) {
        String[] id = stack.getItem().toString().split(":");
        stack.set(DataComponents.ITEM_MODEL, Identifier.of(id[0], id[1]));
    }
}
