package views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import exceptions.ObjectNotFoundException;
import models.User;
import services.Database;
import templates.UserRetrieveTemplate;

import java.io.IOException;
import java.io.OutputStream;


public class UserView implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println(t.getRequestURI());
        String[] urlParts = t.getRequestURI().toString().split("/");
        int status;
        String response = "";
        try {
            response = retrieve(Integer.parseInt(urlParts[2]));
            status = 200;
        }
        catch (ObjectNotFoundException e) {
            status = 404;
        }
        t.sendResponseHeaders(status, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String retrieve(int userId) throws ObjectNotFoundException {
        User user = Database.getUserById(userId);
        return UserRetrieveTemplate.render(user);
    }
}
