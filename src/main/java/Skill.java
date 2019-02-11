import org.json.JSONObject;


class Skill {

    private String name;
    private int points;

    Skill(JSONObject obj) {
        this.name = (String) obj.get("name");
        this.points = (int) obj.get("points");
    }

    String getName() {
        return this.name;
    }

    int getPoints() {
        return this.points;
    }
}
