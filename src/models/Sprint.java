package models;

public class Sprint {

    private static int idCounter = 0;
    private final String name;

    private final int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Sprint(String name) {
        this.name = name;
        this.id = idCounter;
        idCounter++;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
