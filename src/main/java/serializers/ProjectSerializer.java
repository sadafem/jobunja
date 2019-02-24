package serializers;

import models.Project;
import models.Skill;
import org.json.JSONObject;

import java.util.List;


public class ProjectSerializer {

    public static Project deserialize(JSONObject rawData) {
        String id = rawData.getString("id");
        String title= rawData.getString("title");
        String description = rawData.getString("description");
        String imageUrl = rawData.getString("imageUrl");
        int budget = rawData.getInt("budget");
        long deadline = rawData.getInt("deadline");
        List<Skill> requiredSkills = SkillSerializer.deserialize(rawData.getJSONArray("skills"));
        return new Project(id, title, description, imageUrl, budget, deadline, requiredSkills);
    }
}
