package views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import exceptions.ForbiddenException;
import exceptions.ObjectNotFoundException;
import models.Project;
import services.Database;
import templates.ProjectListTemplate;
import templates.ProjectRetrieveTemplate;

import java.io.IOException;
import java.io.OutputStream;


public class ProjectView implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println(t.getRequestURI());
        String[] urlParts = t.getRequestURI().toString().split("/");
        int status;
        String response = "";
        if (urlParts.length == 3) {
            try {
                response = retrieve(urlParts[2]);
                status = 200;
            }
            catch (ObjectNotFoundException e) {
                status = 404;
            }
            catch (ForbiddenException e) {
                status = 403;
            }
        }
        else {
            status = 200;
            response = list();
        }
        t.sendResponseHeaders(status, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String list() {
        return ProjectListTemplate.render(Database.getProjects());
    }

    private String retrieve(String projectId) throws ObjectNotFoundException, ForbiddenException {
        Project project = Database.getProjectById(projectId);
        if (!project.userIsQualified(Database.getUserById(1))) {
            throw new ForbiddenException();
        }
        return ProjectRetrieveTemplate.render(project);
    }
}
