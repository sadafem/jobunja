import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import org.json.JSONArray;
import org.json.JSONObject;


class Project {

    private String title;
    private int budget;
    private List<Skill> requiredSkills = new ArrayList<>();
    private List<Bid> bids = new ArrayList<>();

    Project(String title, JSONArray requiredSkills, int budget) {
        this.title = title;
        this.budget = budget;
        for (Object skillObj : requiredSkills) {
            Skill skill = new Skill((JSONObject) skillObj);
            this.requiredSkills.add(skill);
        }
    }

    String getTitle() {
        return this.title;
    }

    int getBudget() {
        return this.budget;
    }

    List<Skill> getRequiredSkills() {
        return this.requiredSkills;
    }

    void addBid(Bid bid) {
        this.bids.add(bid);
    }

    User performAuction() {
        long bestResult = -1;
        User winner = null;
        for (Bid bid : this.bids) {
            long sum = 0;
            for (Skill requiredSkill : this.requiredSkills) {
                sum += Math.pow((bid.getUser().getSkillByName(requiredSkill.getName()).getPoints() - requiredSkill.getPoints()), 2);
            }
            long result = 10000 * sum + (this.budget - bid.getAmount());
            if (result > bestResult) {
                bestResult = result;
                winner = bid.getUser();
            }
        }
        return winner;
    }
}
