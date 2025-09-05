package com.njackal.logic.model;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface IModelManager {
    void setModel(ItemStack stack, Identifier model);
    void resetModel(ItemStack stack);
}
