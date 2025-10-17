package com.njackal.logic.text;

import com.njackal.lib.text.ITextFormatParser;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;


public class ItemTextManager implements IItemTextManager{

    private final ITextFormatParser textFormatParser;

    public ItemTextManager(ITextFormatParser textFormatParser){
        this.textFormatParser = textFormatParser;
    }

    private LoreBuilder getBuilder(ItemStack stack) {
        return LoreBuilder.of(stack, textFormatParser);
    }

    @Override
    public void renameStack(ItemStack stack,String namePattern) {
        stack.set(DataComponentTypes.ITEM_NAME, textFormatParser.formatText(namePattern));
    }

    @Override
    public void renameCustomStack(ItemStack stack, String namePattern) {
        stack.set(DataComponentTypes.CUSTOM_NAME, textFormatParser.formatText(namePattern));
    }

    @Override
    public void addLoreLine(ItemStack stack, String line) {
        LoreBuilder builder = getBuilder(stack);
        builder.addLine(line);

        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void editLoreLine(ItemStack stack, String line, int lineNum) {
        LoreBuilder builder = getBuilder(stack);
        builder.setLine(lineNum, line);
        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void removeLoreLine(ItemStack stack, int lineNum) {
        LoreBuilder builder = getBuilder(stack);
        builder.removeLine(lineNum);

        stack.set(DataComponentTypes.LORE, builder.build());
    }

    @Override
    public void resetLore(ItemStack stack) {
        LoreBuilder builder = getBuilder(stack);
        builder.reset();
        stack.set(DataComponentTypes.LORE, builder.build());
    }
}
