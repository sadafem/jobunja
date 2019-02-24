package services;

import exceptions.ObjectNotFoundException;
import exceptions.ValidationException;
import models.Project;
import models.Skill;
import models.User;

import java.util.ArrayList;
import java.util.List;


public class Database {

    private static List<User> users = new ArrayList<>();
    private static List<Project> projects = new ArrayList<>();
    private static List<String> skills = new ArrayList<>();

    public static List<Project> getProjects() {
        return projects;
    }

    public static User getUserById(int id) throws ObjectNotFoundException {
        for (User user : users) {
            if (id == user.getId()) {
                return user;
            }
        }
        throw new ObjectNotFoundException();
    }

    public static Project getProjectById(String id) throws ObjectNotFoundException {
        for (Project project : projects) {
            if (id.equals(project.getId())) {
                return project;
            }
        }
        throw new ObjectNotFoundException();
    }

    public static void addUser(User user) throws ValidationException {
        for (Skill skill : user.getSkills()) {
            if (skills.stream().noneMatch(skillName -> skillName.equals(skill.getName()))) {
                throw new ValidationException();
            }
        }
        users.add(user);
    }

    public static void addProject(Project project) {
        projects.add(project);
    }

    public static void addSkill(String name) {
        skills.add(name);
    }
}
