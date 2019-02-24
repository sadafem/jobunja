package serializers;

import models.Skill;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SkillSerializer {

    private static JSONObject serialize(Skill skill) {
        JSONObject result = new JSONObject();
        result.put("name", skill.getName());
        result.put("point", skill.getPoint());
        return result;
    }

    public static JSONArray serialize(List<Skill> skills) {
        JSONArray result = new JSONArray();
        for (Skill skill : skills) {
            result.put(serialize(skill));
        }
        return result;
    }

    public static Skill deserialize(JSONObject rawData) {
        String name = rawData.getString("name");
        int point = rawData.getInt("point");
        return new Skill(name, point);
    }

    static List<Skill> deserialize(JSONArray rawData) {
        List<Skill> skills = new ArrayList<>();
        for (Object skill : rawData) {
            skills.add(deserialize((JSONObject) skill));
        }
        return skills;
    }
}
