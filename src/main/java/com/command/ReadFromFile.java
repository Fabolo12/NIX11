package com.command;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile implements Command {

    private static final Pattern PATTERN_LEVEL = Pattern.compile("^[\\d,:\\- ]+([A-Z]+).*$");
    @SneakyThrows
    @Override
    public void execute() {
        try (BufferedReader br = new BufferedReader(new FileReader("logs/log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(getLevelFromLine(line));
            }
        }
    }

    private String getLevelFromLine(String line) {
        final Matcher matcher = PATTERN_LEVEL.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalStateException("Invalid log");
    }
}
