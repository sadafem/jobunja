package templates;

import models.Project;


public class ProjectRetrieveTemplate {

    public static String render(Project project) {
        return "" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Project</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <ul>\n" +
            "        <li>id: " + project.getId() + "</li>\n" +
            "        <li>title: " + project.getTitle() + "</li>\n" +
            "        <li>description: " + project.getDescription() + "</li>\n" +
            "        <li>\n" +
            "            imageUrl:\n" +
            "            <img src=\"" + project.getImageUrl() + "\" style=\"width: 50px; height: 50px;\">\n" +
            "        </li>\n" +
            "        <li>budget: " + project.getBudget() + "</li>\n" +
            "    </ul>\n" +
            "</body>\n" +
            "</html>\n";
    }
}
