package models;

public class User {

    private static int idCounter = 0;
    private final int id;

    private final String name;

    public User(String name) {
        this.id = idCounter;
        this.name = name;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
