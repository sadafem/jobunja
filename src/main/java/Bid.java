class Bid {

    private User user;
    private Integer amount;

    Bid(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    User getUser() {
        return this.user;
    }

    Integer getAmount() {
        return this.amount;
    }
}
