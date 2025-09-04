package com.njackal.logic.text;

import net.minecraft.item.ItemStack;

public interface IItemTextManager {
    void renameStack(ItemStack stack, String namePattern);

    void addLoreLine(ItemStack stack, String line);
}
