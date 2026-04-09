package com.njackal.logic.glow;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class GlowManager implements IGlowManager {
    @Override
    public void setGlow(ItemStack stack, boolean glowing) {
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, glowing);
    }
}
