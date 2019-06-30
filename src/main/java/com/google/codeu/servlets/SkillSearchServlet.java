package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.io.IOException;
import java.util.*;
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
        Gson gson = new Gson();

        //all users with required skill
        Set<User> selected = datastore.getSkilledUsers(targetSkill);

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
}
