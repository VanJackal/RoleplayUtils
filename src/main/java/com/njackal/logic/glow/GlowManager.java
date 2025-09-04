package com.njackal.logic.glow;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;

public class GlowManager implements IGlowManager {
    @Override
    public void setGlow(ItemStack stack, boolean glowing) {
        stack.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, glowing);
    }
}
