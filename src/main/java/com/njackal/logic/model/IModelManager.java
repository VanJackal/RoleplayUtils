package com.njackal.logic.model;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;

public interface IModelManager {
    void setModel(ItemStack stack, Identifier model);
    void resetModel(ItemStack stack);
}
