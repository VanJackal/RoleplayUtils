package com.njackal.lib.text;

import net.minecraft.network.chat.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatParser implements ITextFormatParser {
    private final Pattern pattern;
    public TextFormatParser() {
        pattern = Pattern.compile("(?<!\\\\)(&)([0-9abcdefrlmnok])", Pattern.CASE_INSENSITIVE);
    }

    public Component formatText(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuilder formattedText = new StringBuilder(text);
        matcher.results().forEach(result ->
                formattedText.replace(result.start(1),result.end(1), "§"));

        return Component.literal(formattedText.toString().replace("\\&", "&"));
    }
}
