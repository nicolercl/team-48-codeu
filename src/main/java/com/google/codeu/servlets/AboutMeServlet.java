package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import com.google.gson.JsonObject;

/**
 * Handles fetching and saving user data.
 */
@WebServlet("/about")
public class AboutMeServlet extends HttpServlet {

    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    /**
     * Responds with the "about me" section for a particular user.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        String user = request.getParameter("user");

        if (user == null || user.equals("")) {
            // Request is invalid, return empty response
            return;
        }

        User userData = datastore.getUser(user);

        if (userData == null || userData.getAboutMe() == null) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", userData.getEmail());
        jsonObject.addProperty("aboutMe", userData.getAboutMe());
        jsonObject.addProperty("learnCate", userData.getLearnCategory());
        jsonObject.addProperty("teachCate", userData.getTeachCategory());
        jsonObject.addProperty("school", userData.getSchool());
        jsonObject.addProperty("name", userData.getName());
        jsonObject.addProperty("age", userData.getAge());
        jsonObject.addProperty("skillLevel", userData.getSkillLevel());
        System.out.println(jsonObject);
        response.getOutputStream().println(jsonObject.toString());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        if (!userService.isUserLoggedIn()) {
            response.sendRedirect("/index.html");
            return;
        }

        String userEmail = userService.getCurrentUser().getEmail();
        User user = datastore.getUser(userEmail);
        String aboutMe = request.getParameter("about-me");
        Whitelist whitelist = Whitelist.basicWithImages();
        whitelist.addTags( "h1", "h2", "h3", "h4", "h5", "h6");
        aboutMe = Jsoup.clean(aboutMe, whitelist);
        user.setAboutMe(aboutMe);
        datastore.storeUser(user);
        response.sendRedirect("/user-page.html?user=" + userEmail);
    }
}
