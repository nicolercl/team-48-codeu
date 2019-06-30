package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.UserTest;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;


@WebServlet("/sksrch")
public class SkillSearchServlet extends HttpServlet {
    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String targetSkill = request.getParameter("skill");
        String paramUsers = request.getParameter("testUsers");
        System.out.println(paramUsers);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<UserTest>>() {
        }.getType();
        ArrayList<UserTest> arr = gson.fromJson(paramUsers, type);

        //all users
        ArrayList<UserTest> users = new ArrayList<UserTest>(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            HashMap<String, String> info = new HashMap<>();
            UserTest user = arr.get(i);
            users.add(user);
        }

        //all categories
        String[] categories = {"Design", "Photography", "Sports", "Music", "Technology", "Language", "Culinary"};

        //users categorized by learnCategory
        HashMap<String, ArrayList<UserTest>> cat_users = categorizeUsers(users, categories);

        //select targetskill users
        ArrayList<UserTest> selected = cat_users.get(targetSkill);

        response.setContentType("application/json");

        if (selected != null) {
            String json = gson.toJson(selected);
            System.out.println(json);
            response.getOutputStream().println(json);
        } else {
            String json = gson.toJson("No matches");
            System.out.println(json);
            response.getOutputStream().println(json);
        }
    }

    //categorize users
    private HashMap<String, ArrayList<UserTest>> categorizeUsers(ArrayList<UserTest> users,
                                                                 String[] categories) {
        HashMap<String, ArrayList<UserTest>> cat_users = new HashMap<>();
        users.forEach((user) -> {
            String skill = user.getTeachCategory();
            if (cat_users.get(skill) == null) {
                ArrayList<UserTest> userList = new ArrayList<>();
                userList.add(user);
                cat_users.put(skill, userList);
            } else {
                cat_users.get(skill).add(user);
            }
        });

        return cat_users;
    }

}
