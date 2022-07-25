package com.command;

import lombok.Getter;

@Getter
public enum Commands {
    CREATE("Create product", new Create()),
    UPDATE("Update product", new Update()),
    PRINT("Print products", new Print()),
    READ_FROM_FILE("Read log level from file", new ReadFromFile()),
    EXIT("Exit", null);

    private final String name;
    private final Command command;

    Commands(String name, Command command) {
        this.name = name;
        this.command = command;
    }
}
