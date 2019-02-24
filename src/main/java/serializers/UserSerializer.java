package serializers;

import models.Skill;
import models.User;
import org.json.JSONObject;

import java.util.List;


public class UserSerializer {

    public static User deserialize(JSONObject rawData) {
        int id = rawData.getInt("id");
        String firstName = rawData.getString("firsName");
        String lastName = rawData.getString("lastName");
        String jobTitle = rawData.getString("jobTitle");
        String profilePictureUrl = rawData.getString("profilePictureUrl");
        String bio = rawData.getString("bid");
        List<Skill> skills = SkillSerializer.deserialize(rawData.getJSONArray("skills"));
        return new User(id, firstName, lastName, jobTitle, profilePictureUrl, bio, skills);
    }
}
