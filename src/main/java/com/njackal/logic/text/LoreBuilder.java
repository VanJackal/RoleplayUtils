package com.njackal.logic.text;

import com.njackal.lib.text.ITextFormatParser;
import com.njackal.lib.text.TextFormatParser;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {
    List<Text> lines;
    private final ITextFormatParser parser;
    public LoreBuilder(LoreComponent init, ITextFormatParser parser) {
        this.parser = parser;
        if (init == null) {
            lines = new ArrayList<>();
        } else {
            lines = new ArrayList<>(init.lines());
        }
    }

    public static LoreBuilder of(ItemStack stack, ITextFormatParser parser) {
        return new LoreBuilder(stack.get(DataComponentTypes.LORE), parser);
    }

    public LoreComponent build() {
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
