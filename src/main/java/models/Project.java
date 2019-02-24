package models;

import exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class Project {

    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private int budget;
    private long deadline;
    private List<Skill> requiredSkills;
    private List<Bid> bids = new ArrayList<>();

    public Project(String id, String title, String description, String imageUrl, int budget, long deadline, List<Skill> requiredSkills) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.budget = budget;
        this.deadline = deadline;
        this.requiredSkills = requiredSkills;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public int getBudget() {
        return this.budget;
    }

    public long getDeadline() {
        return this.deadline;
    }

    public List<Skill> getRequiredSkills() {
        return this.requiredSkills;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public User performAuction() {
        long bestResult = -1;
        User winner = null;
        for (Bid bid : this.bids) {
            long sum = 0;
            for (Skill requiredSkill : this.requiredSkills) {
                try {
                    sum += Math.pow((bid.getUser().getSkillByName(requiredSkill.getName()).getPoint() - requiredSkill.getPoint()), 2);
                }
                catch (ObjectNotFoundException e) {
                    System.out.println("Error: Bidding user skill doesn't satisfy project requirement!");
                }
            }
            long result = 10000 * sum + (this.budget - bid.getAmount());
            if (result > bestResult) {
                bestResult = result;
                winner = bid.getUser();
            }
        }
        return winner;
    }

    public boolean userIsQualified(User user) {
        for (Skill requiredSkill : this.requiredSkills) {
            Skill userSkill;
            try {
                userSkill = user.getSkillByName(requiredSkill.getName());
            }
            catch (ObjectNotFoundException e) {
                return false;
            }
            if (userSkill.getPoint() < requiredSkill.getPoint()) {
                return false;
            }
        }
        return true;
    }
}
