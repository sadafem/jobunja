package models;

import java.util.List;

import exceptions.ObjectNotFoundException;


public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureUrl;
    private String bio;
    private List<Skill> skills;

    public User(int id, String firstName, String lastName, String jobTitle, String profilePictureUrl, String bio, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.skills = skills;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public String getBio() {
        return this.bio;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    Skill getSkillByName(String name) throws ObjectNotFoundException {
        for (Skill skill : this.skills) {
            if (skill.getName().equals(name)) {
                return skill;
            }
        }
        throw new ObjectNotFoundException();
    }
}
