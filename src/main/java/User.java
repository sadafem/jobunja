import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


class User {

    private String username;
    private List<Skill> skills = new ArrayList<>();

    User(String username, JSONArray skills) {
        this.username = username;
        for (int i = 0; i < skills.length(); i++) {
            JSONObject skillObj = (JSONObject) skills.get(i);
            Skill skill = new Skill(skillObj);
            this.skills.add(skill);
        }
    }

    String getUsername() {
        return this.username;
    }

    Skill getSkillByName(String name) {
        for (Skill skill : this.skills) {
            if (skill.getName().equals(name)) {
                return skill;
            }
        }
        return null;
    }

    boolean satisfiesAllSkills(List<Skill> skills) {
        for (Skill skill : skills) {
            Skill userSkill = this.getSkillByName(skill.getName());
            if (userSkill == null || userSkill.getPoints() < skill.getPoints()) {
                return false;
            }
        }
        return true;
    }
}
