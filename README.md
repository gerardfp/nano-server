```
MegaSimpleServer
        .create("localhost", 8002)
        .endpoint("/get", (query, response) -> {
            response.send("<p>Query = " + query + "</p>");
        })
        .endpoint("/", (query, response) -> {
            response.send("<h1>Hello</h1>");
        })
        .start();
```

```
MegaSimpleServer
        .create("localhost",8001)
        .endpoint("/get", (query, res) -> {
            res.send(Files.readString(Paths.get("todos.db")));
        })
        .endpoint("/add", (query, res) -> {
            Files.writeString(Paths.get("todos.db"), query + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            res.send("ok");
        })
        .start();
``