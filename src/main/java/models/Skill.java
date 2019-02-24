package models;


public class Skill {

    private String name;
    private int point;

    public Skill(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return this.name;
    }

    public int getPoint() {
        return this.point;
    }
}
