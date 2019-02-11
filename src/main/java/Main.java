import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


public class Main {

    private static List<User> users = new ArrayList<>();
    private static List<Project> projects = new ArrayList<>();

    private static User getUserByUsername(String name) {
        for (User user : users) {
            if (name.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }


    private static Project getProjectByTitle(String name) {
        for (Project project : projects) {
            if (name.equals(project.getTitle())) {
                return project;
            }
        }
        return null;
    }

    private static void registerUser(JSONObject userInfo) {
        String username = (String) userInfo.get("username");
        JSONArray skills = (JSONArray) userInfo.get("skills");
        User newUser = new User(username, skills);
        users.add(newUser);
    }

    private static void addProject(JSONObject projectInfo) {
        String title = (String) projectInfo.get("title");
        JSONArray requiredSkills = (JSONArray) projectInfo.get("skills");
        int budget = (int) projectInfo.get("budget");
        Project newProject = new Project(title, requiredSkills, budget);
        projects.add(newProject);
    }

    private static void addBid(JSONObject bidInfo) {
        User user = getUserByUsername((String) bidInfo.get("biddingUser"));
        Project project = getProjectByTitle((String) bidInfo.get("projectTitle"));
        Integer amount = (Integer) bidInfo.get("bidAmount");
        if (user.satisfiesAllSkills(project.getRequiredSkills()) && project.getBudget() >= amount) {
            Bid bid = new Bid(user, amount);
            project.addBid(bid);
        }
        else {
            System.out.println("Error: User \"" + user.getUsername() + "\" doesn't satisfy project requirements");
        }
    }

    private static void auction(JSONObject projectID) {
        Project project = getProjectByTitle((String) projectID.get("projectTitle"));
        User winner = project.performAuction();
        if (winner != null) {
            System.out.println("Winner: " + winner.getUsername());
        }
        else {
            System.out.println("Project has no winner");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isFinished = false;

        while (!isFinished) {
            String line = scanner.nextLine();
            int spaceIndex = line.indexOf(" ");
            String command = line.substring(0, spaceIndex);
            String rawData = line.substring(spaceIndex);
            JSONObject data = new JSONObject(rawData);

            switch (command) {

                case "register":
                    registerUser(data);
                    break;

                case "addProject":
                    addProject(data);
                    break;

                case "bid":
                    addBid(data);
                    break;

                case "auction":
                    auction(data);
                    isFinished = true;
                    break;

                default:
                    System.out.println("Error: Invalid command!");
                    break;
            }
        }
    }
}
