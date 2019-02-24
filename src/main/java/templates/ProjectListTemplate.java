package templates;

import models.Project;

import java.util.List;


public class ProjectListTemplate {

    public static String render(List<Project> projects) {
        StringBuilder s = new StringBuilder();
        s.append("<!DOCTYPE html>\n");
        s.append("<html lang=\"en\">\n");
        s.append("<head>\n");
        s.append("    <meta charset=\"UTF-8\">\n");
        s.append("    <title>Projects</title>\n");
        s.append("    <style>\n");
        s.append("        table {\n");
        s.append("            text-align: center;\n");
        s.append("            margin: 0 auto;\n");
        s.append("        }\n");
        s.append("        td {\n");
        s.append("            min-width: 300px;\n");
        s.append("            margin: 5px 5px 5px 5px;\n");
        s.append("            text-align: center;\n");
        s.append("        }\n");
        s.append("    </style>\n");
        s.append("</head>\n");
        s.append("<body>\n");
        s.append("    <table>\n");
        s.append("        <tr>\n");
        s.append("            <th>id</th>\n");
        s.append("            <th>title</th>\n");
        s.append("            <th>budget</th>\n");
        s.append("        </tr>\n");
        for (Project project : projects) {
            s.append("        <tr>\n");
            s.append("            <th>");
            s.append("                <a href=\"/project/").append(project.getId()).append("/\">\n");
            s.append("                    ").append(project.getId()).append("\n");
            s.append("                </a>\n");
            s.append("            </th>\n");
            s.append("            <th>").append(project.getTitle()).append("</th>\n");
            s.append("            <th>").append(project.getBudget()).append("</th>\n");
            s.append("        </tr>\n");
        }
        s.append("    </table>\n");
        s.append("</body>\n");
        s.append("</html>\n");
        return s.toString();
    }
}
