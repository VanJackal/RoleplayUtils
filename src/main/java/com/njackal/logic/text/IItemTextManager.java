package com.njackal.logic.text;

import net.minecraft.item.ItemStack;

public interface IItemTextManager {
    void renameStack(ItemStack stack, String namePattern);
    void renameCustomStack(ItemStack stack, String namePattern);

    void addLoreLine(ItemStack stack, String line);

    void editLoreLine(ItemStack stack, String line, int lineNum);

    void removeLoreLine(ItemStack stack, int lineNum);
    void resetLore(ItemStack stack);
}
