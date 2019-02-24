package models;


public class Bid {

    private User user;
    private Project project;
    private int amount;

    public Bid(User user, Project project, int amount) {
        this.user = user;
        this.project = project;
        this.amount = amount;
    }

    User getUser() {
        return this.user;
    }

    Project getProject() {
        return this.project;
    }

    Integer getAmount() {
        return this.amount;
    }
}
