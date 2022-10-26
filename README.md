## install

Add to `build.gradle`
``` groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // ...
    implementation 'com.github.gerardfp:nano-server:0.1'
}

```

## use
```java
NanoServer
        .create("localhost", 8002)
        .endpoint("/", (query, response) -> {
            response.send("<h1>Hello</h1>");
        })
        .endpoint("/get", (query, response) -> {
            response.send("<p>Query = " + query + "</p>");
        })
        .start();
```

```java
NanoServer
        .create("localhost",8001)
        .endpoint("/get", (query, res) -> {
            res.send(Files.readString(Paths.get("todos.db")));
        })
        .endpoint("/add", (query, res) -> {
            Files.writeString(Paths.get("todos.db"), query + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            res.send("ok");
        })
        .start();
```
