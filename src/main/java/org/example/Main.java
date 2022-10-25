package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) {
        MegaSimpleServer
                .create("localhost",8001)
                .endpoint("/get", (query, res) -> {
                    String todos = Files.readString(Paths.get("todos.db"));
                    res.send(todos);
                })
                .endpoint("/add", (query, res) -> {
                    Files.writeString(Paths.get("todos.db"), query + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    res.send("ok");
                })
                .start();

    }
}
