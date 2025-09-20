package com.njackal.lib.text;

import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatParser {
    private final Pattern pattern;
    public TextFormatParser() {
        pattern = Pattern.compile("(?<!\\\\)(&)([0-9rlmnok])", Pattern.CASE_INSENSITIVE);
    }

    public Text formatText(String text) {
        return Text.literal(formatString(text));
    }

    public String formatString(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuilder formattedText = new StringBuilder(text);
        matcher.results().forEach(result ->
                formattedText.replace(result.start(1),result.end(1), "§"));

        return formattedText.toString().replace("\\&", "&");
    }
}
