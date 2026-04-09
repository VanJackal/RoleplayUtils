package com.njackal.logic.text;

import com.njackal.lib.text.ITextFormatParser;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {
    List<Component> lines;
    private final ITextFormatParser parser;
    public LoreBuilder(ItemLore init, ITextFormatParser parser) {
        this.parser = parser;
        if (init == null) {
            lines = new ArrayList<>();
        } else {
            lines = new ArrayList<>(init.lines());
        }
    }

    public static LoreBuilder of(ItemStack stack, ITextFormatParser parser) {
        return new LoreBuilder(stack.get(DataComponents.LORE), parser);
    }

    public ItemLore build() {
        return new LoreComponent(lines);
    }

    public void reset(){
        lines = List.of();
    }

    public void addLine(String line) {
        lines.add(parser.formatText(line));
    }

    public void setLine(int index, String line) {
        if (index >= lines.size()) return;
        lines.set(index, parser.formatText(line));
    }

    public void removeLine(int index) {
        if (index >= lines.size()) return;
        lines.remove(index);
    }
}
