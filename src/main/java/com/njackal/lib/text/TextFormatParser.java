package com.njackal.lib.text;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatParser {
    private final Pattern pattern;
    public TextFormatParser() {
        pattern = Pattern.compile("&(\\S)");
    }

    public Text format(String text) {
        Matcher matcher = pattern.matcher(text);
        System.out.println(text);
        Text out;
        String newText = text.replace("&", "§");
        out = Text.literal(newText);
        return out;
    }
}
