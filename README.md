```
public class Main {
    public static void main(String[] args) {
        MegaSimpleServer server = MegaSimpleServer.create("localhost",8001);

        server.endpoint("/get", (query, res) -> {
            String todos = Files.readString(Paths.get("todos.db"));
            res.send(todos);
        });

        server.endpoint("/add", (query, res) -> {
            Files.writeString(Paths.get("todos.db"), query + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            res.send("ok");
        });

        server.start();
        System.out.println("Server started on port 8001");
    }
}
``