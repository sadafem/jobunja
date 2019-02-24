import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import exceptions.ValidationException;
import models.Skill;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import serializers.ProjectSerializer;
import services.Database;
import services.Server;


public class Main {

    private static void print(Object object) {
        System.out.println(object);
    }

    private static String performGetRequest(String address) throws Exception {
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        print("Sending request to " + address);
        int status = con.getResponseCode();
        if (status == 200) {
            print("Request succeeded.");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();
        }
        else {
            throw new Exception();
        }
    }

    private static void fetchInitialData() throws Exception {
        String rawData = performGetRequest("http://142.93.134.194:8000/joboonja/project");
        JSONArray projects = new JSONArray(rawData);
        for (Object project : projects) {
            Database.addProject(ProjectSerializer.deserialize((JSONObject) project));
        }

        rawData = performGetRequest("http://142.93.134.194:8000/joboonja/skill");
        JSONArray skills = new JSONArray(rawData);
        for (Object skill : skills) {
            Database.addSkill(((JSONObject) skill).getString("name"));
        }
    }

    public static void main(String[] args) {
        try {
            fetchInitialData();
        }
        catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                print("Error: Request timed-out! Turn off your VPN if you have one.");
            }
            else {
                print("Error: Request failed!");
            }
            return;
        }

        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("HTML", 5));
        skills.add(new Skill("Javascript", 4));
        skills.add(new Skill("C++", 2));
        skills.add(new Skill("Java", 3));
        try {
            Database.addUser(new User(
                    1,
                    "علی",
                    "شریف‌زاده",
                    "برنامه‌نویس وب",
                    "",
                    "روی سنگ قبرم بنویسید: خدابیامرز می‌خواست خیلی کارا بکنه ولی پول نذاشت",
                    skills
            ));
        }
        catch (ValidationException e) {
            System.out.println("Error: Validation exception occurred!");
            return;
        }

        try {
            Server.start();
        }
        catch (Exception e) {
            print("Error: internal Server error!");
            print(e);
        }
    }
}
