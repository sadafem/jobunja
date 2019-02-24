package services;

import com.sun.net.httpserver.HttpServer;
import views.ProjectView;
import views.UserView;

import java.net.InetSocketAddress;


public class Server {

    public static void start() throws Exception {
        System.out.println("Starting HTTP server.");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/project", new ProjectView());
        server.createContext("/user", new UserView());
        server.setExecutor(null);
        server.start();
        System.out.println("HTTP server started.");
    }
}
